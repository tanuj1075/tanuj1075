<!DOCTYPE html>
<html>
<head>
    <title>jQuery before() and after()</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<p id="para">This is a paragraph.</p>

<button onclick="addText()">Add Text Before & After</button>

<script>
function addText() {
    $("#para").before("<p>This is text BEFORE paragraph</p>");
    $("#para").after("<p>This is text AFTER paragraph</p>");
}
</script>

</body>
</html>
