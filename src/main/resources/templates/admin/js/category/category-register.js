$(document).ready(function() {
    $('#firstParentCategoryId').change(function() {
        var firstParentCategoryId = $(this).val();
        $.ajax({
            type: 'GET',
            url: '/admin/ajax/category?parentCategoryId=' + firstParentCategoryId,
            success: function(data) {
                $('#secondParentCategoryId').empty();
                $('#secondParentCategoryId').append('<option value="">없음</option>');
                $.each(data, function(index, category) {
                    $('#secondParentCategoryId').append('<option value="' + category.id + '">' + category.name + '</option>');
                });
            }
        });
    });
});