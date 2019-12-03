function createContractRow(pnumber) {
    var tr = document.createElement('tr');
    var pnumberCell = document.createElement('td');
    var actionCell = document.createElement('td');

    var link = document.createElement('a');
    link.href = '/dashboard/tariffs?phoneNumber=' + pnumber;
    link.title = 'Choose a contract';
    link.appendChild(document.createTextNode('Select'));
    actionCell.appendChild(link);

    pnumberCell.textContent = pnumber;

    tr.appendChild(pnumberCell);
    tr.appendChild(actionCell);

    return tr;
}

function freeContractsLoaded(loadedContracts) {
    var tbody = document.getElementById('free-contracts-list');
    clearNode(tbody);

    for (var index = 0; index < loadedContracts.length; index++) {
        var contract = loadedContracts[index];
        var pnumber = contract.phoneNumber;

        var tr = createContractRow(pnumber);
        tbody.appendChild(tr);
    }
}

function loadFreeContractsFailed() {
    var tbody = document.getElementById('free-contracts-list');
    clearNode(tbody);

    var tr = document.createElement('tr');
    var errorMessageCell = document.createElement('td');
    errorMessageCell.colSpan = 2;
    errorMessageCell.textContent = 'Failed to load free contracts';

    tr.appendChild(errorMessageCell);
    tbody.appendChild(tr);
}

function loadFreeContracts() {
    var request = new XMLHttpRequest();
    request.open("get", "/api/contracts/findFree", true);
    request.onreadystatechange = function (ev) {
        if (request.readyState === 4) {
            if (request.status === 200) {
                freeContractsLoaded(JSON.parse(request.responseText));
            } else {
                loadFreeContractsFailed();
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
