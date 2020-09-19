let cars;

async function fetchAll() {
    let url = "https://marcge.dk/CA/api/cars/all";
    let makeTable = document.getElementById("carTable");
    return await fetch(url).then(res => res.json())
}

async function makeTable(data) {
    let makeTable = document.getElementById("carTable")
    let dataArr = await data
    let newArray = dataArr.map(x =>
            `<tr id="${x.id}">
                    <td>${x.id}</td>
                    <td>${x.year}</td>
                    <td>${x.make}</td>
                    <td>${x.model}</td>
                    <td>${x.price}</td>
            </tr>`)
    makeTable.innerHTML =
            `<thead>
                <th>Id</th>
                <th>Year</th>
                <th>Make</th>
                <th>Model</th>
                <th>Price</th>
            </thead>
                ${newArray.join("")}`
}

async function ReverseOrResetCars(reverse) {
    cars = await getCars()
    if (reverse === true)
        cars.reverse()
    if (reverse === false)
        cars = await fetchAll()
    makeTable(cars)
}

async function getCars() {
    if (cars === undefined)
        return await fetchAll()
    return cars
}

async function sortChoice() {
    let carArray = await getCars()

    if (sort.value === "0")
        cars = carArray.sort((a, b) => compare(a.id, b.id))
    if (sort.value === "1")
        cars = carArray.sort((a, b) => compare(a.year, b.year))
    if (sort.value === "2")
        cars = carArray.sort((a, b) => compare(a.make, b.make))
    if (sort.value === "3")
        cars = carArray.sort((a, b) => compare(a.model, b.model))
    if (sort.value === "4")
        cars = carArray.sort((a, b) => compare(a.price, b.price))
    makeTable(cars)
}

async function filterChoice() {
    let carArray = await fetchAll()
    let filterInput = document.getElementById("filterInput")

    if (filterList.value === "0")
        cars = carArray.filter(n => n.id < filterInput.value)
    if (filterList.value === "1")
        cars = carArray.filter(n => n.id > filterInput.value)
    if (filterList.value === "2")
        cars = carArray.filter(n => n.year < filterInput.value)
    if (filterList.value === "3")
        cars = carArray.filter(n => n.year > filterInput.value)
    if (filterList.value === "6")
        cars = carArray.filter(n => n.price < filterInput.value)
    if (filterList.value === "7")
        cars = carArray.filter(n => n.price > filterInput.value)
    if (filterList.value === "4")
        cars = carArray.filter(n => n.make.toLowerCase().includes(filterInput.value.toLowerCase()))
    if (filterList.value === "5")
        cars = carArray.filter(n => n.model.toLowerCase().includes(filterInput.value.toLowerCase()))
    makeTable(cars)
}

function compare(a, b) {
    if (typeof a === "string")
        a.toLowerCase()
    if (typeof b === "string")
        b.toLowerCase()
    let aVal = a
    let bVal = b
    if (aVal < bVal)
        return -1
    if (aVal > bVal)
        return 1
    return 0
}