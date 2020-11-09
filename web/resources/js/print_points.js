
function print_points()
{

    document.getElementById('table_data').childNodes.forEach(row => {
        let cells = row.childNodes;
        if (cells[1] != undefined) {
            let x = parseFloat(cells[0].innerText);
            let y = parseFloat(cells[1].innerText);
            let result = parseFloat(cells[3].innerText);

            console.log("x: " + cells[0].innerText + " y: " + cells[1].innerText + " result " + result);
            if (isNaN(x) || isNaN(y)) {
                return;
            }
            drawPointWithResult(x, y, result);
        }
    });
}