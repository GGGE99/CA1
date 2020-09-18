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

async function sortChoice() {
    let carArray = await fetchAll()
    console.log(carArray);
    let tableArray
    if (sort.value === "0")
        tableArray = carArray.sort((a, b) => compare(a.id, b.id))
    if (sort.value === "1")
        tableArray = carArray.sort((a, b) => compare(a.year, b.year))
    if (sort.value === "2")
        tableArray = carArray.sort((a, b) => compare(a.make, b.make))
    if (sort.value === "3")
        tableArray = carArray.sort((a, b) => compare(a.model, b.model))
    if (sort.value === "4")
        tableArray = carArray.sort((a, b) => compare(a.price, b.price))

    makeTable(tableArray)
}

async function filterChoice() {
    let carArray = await fetchAll()
    let tableArray
    let filterInput = document.getElementById("filterInput")

    if (filterList.value === "0")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.id, 1))
    if (filterList.value === "1")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.id, 2))
    if (filterList.value === "2")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.year, 1))
    if (filterList.value === "3")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.year, 2))
    if (filterList.value === "6")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.price, 1))
    if (filterList.value === "7")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.price, 2))

    if (filterList.value === "4")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.make))
    if (filterList.value === "5")
        tableArray = carArray.filter(n => filterHigeOrLow(filterInput.value, n.model))

    makeTable(tableArray)
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

function filterHigeOrLow(input, n, i) {
    console.log(n);
    if (i === 1)
        return (n < input)
    if (i === 2)
        return (n > input)
    if (typeof n === "string")
        return n.toLowerCase().includes(input.toLowerCase())
    
    return null

}