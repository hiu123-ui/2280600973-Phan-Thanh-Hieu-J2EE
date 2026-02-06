$(document).ready(function () {

    $.ajax({
        url: 'http://localhost:8080/api/v1/books',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            let trHTML = '';
            $.each(data, function (i, item) {
                trHTML += '<tr id="book-' + item.id + '">' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.title + '</td>' +
                    '<td>' + item.author + '</td>' +
                    '<td>' + item.price + '</td>' +
                    '<td>' + item.category + '</td>' +
                    '<td>' +
                    // Nút Edit và Delete
                    '<a href="/books/edit/' + item.id + '" class="btn btn-primary btn-sm">Edit</a> ' +
                    '<button class="btn btn-danger btn-sm" onclick="apiDeleteBook(' + item.id + ')">Delete</button> ' +

                    // NÚT ADD TO CART MỚI (Dùng hàm JavaScript để xử lý)
                    '<button class="btn btn-success btn-sm" onclick="addToCart(' +
                    item.id + ', \'' + item.title + '\', ' + item.price + ')">Add to cart</button>' +
                    '</td>' +
                    '</tr>';
            });
            $('#book-table-body').append(trHTML);
        }
    });
});


function apiDeleteBook(id) {
    if (confirm('Are you sure you want to delete this book?')) {
        $.ajax({
            url: 'http://localhost:8080/api/v1/books/' + id,
            type: 'DELETE',
            success: function () {
                alert('Book deleted successfully!');
                $('#book-' + id).remove();
            },
            error: function () {
                alert('Error deleting book! Check your permissions.');
            }
        });
    }
    
}
function addToCart(id, name, price) {
    // Tạo một Form tạm thời
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '/books/add-to-cart'; // Đường dẫn phải khớp với @PostMapping của bạn

    // Thêm các tham số vào Form
    const params = { id, name, price, quantity: 1 };
    
    for (const key in params) {
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = key;
        input.value = params[key];
        form.appendChild(input);
    }

    // Quan trọng: Thêm CSRF Token nếu Security của Minh đang bật
    // Nếu Minh đã tắt CSRF cho /books/** thì bỏ đoạn này đi
    const csrfToken = document.querySelector('input[name="_csrf"]')?.value;
    if (csrfToken) {
        const csrfInput = document.createElement('input');
        csrfInput.type = 'hidden';
        csrfInput.name = '_csrf';
        csrfInput.value = csrfToken;
        form.appendChild(csrfInput);
    }

    document.body.appendChild(form);
    form.submit(); // Thực hiện chuyển hướng giống như nút bấm cũ
}