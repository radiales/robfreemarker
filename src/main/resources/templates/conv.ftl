<html>
<head>
    <title>Quiz</title>
</head>
<style>
    html, body {
        border: 0;
        outline: 0;
        margin: 0;
        width: 100%;
        height: 100%;
        display: block;
        font-size: 22pt;
    }

    * {
        font-family: Segoe UI, sans-serif;
        user-select: none;
        -moz-user-select: none;
    }

    #main{
        display: flex;
        flex-direction: row;
        height: 100vh;
    }

    #wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 70%;
    }

    #wrapper > * {
        text-align: center;
    }

    .sideDiv{
        display: flex;
        width: 15%;
        align-items: center;
        justify-content: center;
        cursor: pointer;
    }

    .sideDiv > span {
        font-size: 50pt;
    }

    #rightDiv{
        background-image: linear-gradient(270deg, #222, #CCC);
    }

    #leftDiv{
        background-image: linear-gradient(90deg, #222, #CCC);
    }

    #leftDiv > span {
        transform: rotate(180deg);
    }

    input {
        padding: 25px;
        cursor: pointer;
        font-size: 22pt;
    }

    #answer {
        visibility: hidden;
    }

    #questionMark {
        font-size: 42pt;
    }
</style>
<body>
<div id="main">
    <div id="leftDiv" class="sideDiv" onclick="previousQuestion()"><span>➜</span></div>
    <div id="wrapper">
        <span id="questionMark">❔</span>
        <div>
            <h2>Frage:</h2>
            <p id="question">Frage</p><br>
        </div>
        <div>
            <h2>Antwort:</h2>
            <p id="answer">Antwort</p><br>
        </div>
        <input type="button" value="Antwort anzeigen" onclick="showAnswer()"/>
    </div>
    <div id="rightDiv" class="sideDiv" onclick="nextQuestion()"><span>➜</span></div>
</div>
<script>

    let qNode = document.querySelector("#question");
    let aNode = document.querySelector("#answer");

    let questions =
        [
            <#list systems as system>
                [
                    "${system.getQuestion()}",
                    "${system.getAnswer()}"
                ],
            </#list>
        ];

    let index = 0;
    let currentQuestion = questions[index];

    qNode.innerHTML = currentQuestion[0];
    aNode.innerHTML = currentQuestion[1];

    function showAnswer(){
        aNode.style.visibility = "visible";
    }

    function nextQuestion(){
        if(index + 1 > questions.length - 1) return;
        index++;
        currentQuestion = questions[index];

        qNode.innerHTML = currentQuestion[0];
        aNode.innerHTML = currentQuestion[1];
        aNode.style.visibility = "hidden";
    }

    function previousQuestion() {
        if(index == 0) return;
        index--;

        currentQuestion = questions[index];

        qNode.innerHTML = currentQuestion[0];
        aNode.innerHTML = currentQuestion[1];
        aNode.style.visibility = "hidden";
    }
</script>
</body>
</html>