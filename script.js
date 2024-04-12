document.getElementById('transaction-form').addEventListener('submit', function(e) {
    e.preventDefault();

    var descriptionInput = document.getElementById('description');
    var amountInput = document.getElementById('amount');
    var categoryInput = document.getElementById('category');

    var description = descriptionInput.value;
    var amount = parseFloat(amountInput.value);
    var category = categoryInput.value;

    if(description === '' || isNaN(amount) || amount <= 0) {
        alert('Please enter valid description and amount');
        return;
    }

    addTransaction(description, amount, category);

    descriptionInput.value = '';
    amountInput.value = '';
});

function addTransaction(description, amount, category) {
    var transactionList = document.getElementById('transaction-list');

    var div = document.createElement('div');
    div.classList.add('transaction-item');
    div.innerHTML = `<strong>${description}</strong>: $${amount} (${category})`;
    transactionList.appendChild(div);

    updateChart();
}

function updateChart() {
    var categories = [];
    var amounts = [];

    var transactionItems = document.querySelectorAll('.transaction-item');
    transactionItems.forEach(function(item) {
        var text = item.textContent;
        var category = text.substring(text.lastIndexOf('(') + 1, text.lastIndexOf(')'));
        var amount = parseFloat(text.substring(text.indexOf('$') + 1, text.lastIndexOf('(') - 1));

        if(categories.includes(category)) {
            var index = categories.indexOf(category);
            amounts[index] += amount;
        } else {
            categories.push(category);
            amounts.push(amount);
        }
    });

    var ctx = document.getElementById('transaction-chart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: categories,
            datasets: [{
                label: 'Amount',
                data: amounts,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Initialize chart on page load
updateChart();
