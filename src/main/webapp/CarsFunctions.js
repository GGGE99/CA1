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

async function sortYear() {
    let carArray = await fetchAll()
    let newcacarArray = carArray.sort((a, b) => {
        let aVal = a.year
        let bVal = b.year
        if (aVal < bVal)
            return -1
        if (aVal > bVal)
            return 1
        return 0
    })
    makeTable(newcacarArray)
}
async function sortId() {
    let carArray = await fetchAll()
    let newcacarArray = carArray.sort((a, b) => {
        let aVal = a.id
        let bVal = b.id
        if (aVal < bVal)
            return -1
        if (aVal > bVal)
            return 1
        return 0
    })
    makeTable(newcacarArray)
}

async function sortPrice() {
    let carArray = await fetchAll()
    let newcacarArray = carArray.sort((a, b) => {
        let aVal = a.price
        let bVal = b.price
        if (aVal < bVal)
            return -1
        if (aVal > bVal)
            return 1
        return 0
    })
    makeTable(newcacarArray)
}

async function sortModel() {
    let carArray = await fetchAll()
    let newcacarArray = carArray.sort((a, b) => {
        let aVal = a.model.toLowerCase()
        let bVal = b.model.toLowerCase()
        if (aVal < bVal)
            return -1
        if (aVal > bVal)
            return 1
        return 0
    })
    makeTable(newcacarArray)
}

async function sortMake() {
    let carArray = await fetchAll()
    let newcacarArray = carArray.sort((a, b) => {
        let aVal = a.make.toLowerCase()
        let bVal = b.make.toLowerCase()
        if (aVal < bVal)
            return -1
        if (aVal > bVal)
            return 1
        return 0
    })
    makeTable(newcacarArray)
}

async function filterId(i, input) {
    let carArray = await fetchAll()
    let newcacarArray
    if (i === 1)newcacarArray = carArray.filter(n => n.id < input)
    if (i === 2)newcacarArray = carArray.filter(n => n.id > input)
    makeTable(newcacarArray)
}

async function filterYear(i, input) {
    let carArray = await fetchAll()
    let newcacarArray
    if (i === 1)newcacarArray = carArray.filter(n => n.year < input)
    if (i === 2)newcacarArray = carArray.filter(n => n.year > input)
    makeTable(newcacarArray)
}

async function filterPrice(i, input) {
    let carArray = await fetchAll()
    let newcacarArray
    if (i === 1)newcacarArray = carArray.filter(n => n.price < input)
    if (i === 2)newcacarArray = carArray.filter(n => n.price > input)
    makeTable(newcacarArray)
}

async function filterMake(input) {
    let carArray = await fetchAll()
    let newcacarArray = carArray.filter(n => 
    n.make.toLowerCase().includes(input.toLowerCase()))
    makeTable(newcacarArray)
}

async function filterModel(input) {
    let carArray = await fetchAll()
    let newcacarArray = carArray.filter(n => 
    n.model.toLowerCase().includes(input.toLowerCase()))
    makeTable(newcacarArray)
}