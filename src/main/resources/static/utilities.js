$(document).ready(function() {
    $("#filterInput").keyup(function(){
        var input = $(this).val().toLowerCase();
        $("#resultsTable tbody tr").each(function() {
            var text = $(this).text().toLowerCase();
            if ((text.indexOf(input) > -1) && (text != "")) {
                $(this).show();
            }
            else
            {
                $(this).hide();
            }
        });
    });
});