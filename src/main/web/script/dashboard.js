function createContractRow(pnumber, tname, tprice, cname, csurmame, contractid) {
    var tr = document.createElement('tr');
    var pnumberCell = document.createElement('td');
    var tnameCell = document.createElement('td');
    var tpriceCell = document.createElement('td');
    var cnameCell = document.createElement('td');
    var csurnameCell = document.createElement('td');
    var optionCell = document.createElement('td');

    var link = document.createElement('a');
    link.href = '/dashboard/options?contractId=' + contractid;
    link.title = 'Show connected options';
    link.appendChild(document.createTextNode('Details'));
    optionCell.appendChild(link);

    pnumberCell.textContent = pnumber;
    tnameCell.textContent = tname;
    tpriceCell.textContent = tprice;
    cnameCell.textContent = cname;
    csurnameCell.textContent = csurmame;

    tr.appendChild(pnumberCell);
    tr.appendChild(tnameCell);
    tr.appendChild(tpriceCell);
    tr.appendChild(cnameCell);
    tr.appendChild(csurnameCell);
    tr.appendChild(optionCell);

    return tr;
}

function contractsLoaded(loadedContracts) {
    var tbody = document.getElementById('contracts-list');
    clearNode(tbody);

    for (var index = 0; index < loadedContracts.length; index++) {
        var contract = loadedContracts[index];
        var pnumber = contract.phoneNumber;
        var tname = contract.tariff.name;
        var tprice = contract.tariff.price.toString();
        var cname = contract.client.firstName;
        var csurmame = contract.client.lastName;
        var contractid = contract.id;

        var tr = createContractRow(pnumber, tname, tprice, cname, csurmame, contractid);
        tbody.appendChild(tr);
    }
}

function loadContractsFailed() {
    var tbody = document.getElementById('contracts-list');
    clearNode(tbody);

    var tr = document.createElement('tr');
    var errorMessageCell = document.createElement('td');
    errorMessageCell.colSpan = 6;
    errorMessageCell.textContent = 'Failed to load transactions';

    tr.appendChild(errorMessageCell);
    tbody.appendChild(tr);
}

function loadContracts(id) {
    var request = new XMLHttpRequest();
    request.open("get", "/api/contracts/find?clientId=" + id, true);
    request.onreadystatechange = function (ev) {
        if (request.readyState === 4) {
            if (request.status === 200) {
                contractsLoaded(JSON.parse(request.responseText));
            } else {
                loadContractsFailed();
            }
        }
    };
    request.send();
}

function clearNode(node) {
    while (node.hasChildNodes()) {
        node.removeChild(node.firstChild);
    }
}
