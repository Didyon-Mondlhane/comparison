function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('collapsed');
    
    const isCollapsed = sidebar.classList.contains('collapsed');
    localStorage.setItem('sidebarCollapsed', isCollapsed);
}

document.addEventListener('DOMContentLoaded', function() {
    const sidebar = document.getElementById('sidebar');
    const isCollapsed = localStorage.getItem('sidebarCollapsed') === 'true';
    
    if (isCollapsed) {
        sidebar.classList.add('collapsed');
    }
    
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const addActivityBtn = document.getElementById('add-activity');
    if (addActivityBtn) {
        addActivityBtn.addEventListener('click', function() {
        });
    }
    
    const deleteForms = document.querySelectorAll('form[action*="delete"]');
    deleteForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!confirm('Tem certeza que deseja excluir este item?')) {
                e.preventDefault();
            }
        });
    });
});

function initCharts() {
    const chartElements = document.querySelectorAll('.chart');
    chartElements.forEach(element => {
        const ctx = element.getContext('2d');
        const chartType = element.dataset.chartType || 'bar';
        const chartData = JSON.parse(element.dataset.chartData);
        
        new Chart(ctx, {
            type: chartType,
            data: chartData,
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', initCharts);