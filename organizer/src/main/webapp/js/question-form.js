var i = 0;
/* Set Global Variable i */
function increment() {
    i += 1;
    /* Function for automatic increment of field's "Name" attribute. */
}
var q = 0;
/* Set Global Variable i */
function questNumIncrement() {
    q += 1;
    /* Function for automatic increment of field's "Name" attribute. */
}
var c = 0;
/* Set Global Variable i */
function criteriaNumIncrement() {
    c += 1;
    /* Function for automatic increment of field's "Name" attribute. */
}
/*
 ---------------------------------------------

 Function to Remove Form Elements Dynamically
 ---------------------------------------------

 */

function removeElement(elementId) {
    var element = document.getElementById(elementId);
    element.outerHTML = '';
}

function removeQuestionBlock(variantId) {
    var lastQuestNum = document.getElementsByClassName(variantId + "_question").length;

    removeElement(variantId + "_question" + lastQuestNum);
}

function removeCriteriaElement(questionId) {
    var bootstrap = "form-control input-xxlarge";
    var lastCritNum = document.getElementsByClassName(bootstrap + " " + questionId + "_criterion").length;

    removeElement(questionId + "_criterion" + lastCritNum);
}

// function removeElement(parentDiv, childDiv) {
//     if (childDiv == parentDiv) {variantId
//         alert("The parent div cannot be removed.");
//     }
//     else if (document.getElementById(childDiv)) {
//         var child = document.getElementById(childDiv);
//         var parent = document.getElementById(parentDiv);
//         parent.removeChild(child);
//     }
//     else {
//         alert("Child div has already been removed or does not exist.");
//         return false;
//     }
// }


function addQuestionBlock() {
    var questDiv = createQuestDivFunction();
    document.getElementById("myForm").appendChild(questDiv);
}

function addQuestionBlock(parentDivId) {
    var questDiv = createQuestDivFunction(parentDivId);
    document.getElementById(parentDivId).appendChild(questDiv);
}

function createQuestDivFunction(parentDivId) {

    var questDiv = document.createElement('DIV');
    var questClassName = parentDivId + "_question";
    var tabQuestNum = document.getElementsByClassName(questClassName).length;
    var questId = questClassName + ++tabQuestNum;
    //tabQuestNum++;
    questDiv.setAttribute("id", questId);
    questDiv.setAttribute("class", questClassName);

    var questLine = document.createElement('HR');
    questDiv.appendChild(questLine);
    var questHeader = document.createElement('h2');
    questHeader.innerText = "Вопрос " + tabQuestNum;
    questDiv.appendChild(questHeader);

    var questTextInput = document.createElement("INPUT");
    questTextInput.setAttribute("type", "text");
    questTextInput.setAttribute("class", "form-control input-xxlarge");
    questTextInput.setAttribute("placeholder", "Текст вопроса...");
    questTextInput.setAttribute("name", questId + "_text");
    questDiv.appendChild(questTextInput);

    questDiv.appendChild(document.createElement("p"));

    var questAnswerInput = document.createElement("TEXTAREA");
    questAnswerInput.setAttribute("type", "text");
    questAnswerInput.setAttribute("class", "form-control input-xxlarge");
    questAnswerInput.setAttribute("placeholder", "Текст ответа...");
    questAnswerInput.setAttribute("name", questId + "_answer");
    questDiv.appendChild(questAnswerInput);

    //var p2 = document.createElement("p");
    questDiv.appendChild(document.createElement("p"));

    var questCriteriaAddButton = document.createElement("BUTTON");
    questCriteriaAddButton.innerText = "Добавить критерий"
    questCriteriaAddButton.setAttribute("type", "button");
    questCriteriaAddButton.setAttribute("class", "btn-sm btn-success");
    questCriteriaAddButton.setAttribute("onclick", "addCriteriaElement('" + questId + "')");
    questDiv.appendChild(questCriteriaAddButton);

    var questCriteriaRemoveButton = document.createElement("BUTTON");
    questCriteriaRemoveButton.innerText = "Удалить критерий"
    questCriteriaRemoveButton.setAttribute("type", "button");
    questCriteriaRemoveButton.setAttribute("class", "btn-sm btn-danger");
    questCriteriaRemoveButton.setAttribute("onclick", "removeCriteriaElement('" + questId + "')");
    questDiv.appendChild(questCriteriaRemoveButton);

    return questDiv;

    document.getElementById("myForm").appendChild(questDiv);

}


// function  createQuestDivFunction(){ //TODO заменить название функции
//
//     var questDiv = document.createElement('DIV');
//     questNumIncrement();
//     questDiv.setAttribute("id", "quest_" + q);
//     questDiv.setAttribute("")
//
//     var questLine = document.createElement('HR');
//     questDiv.appendChild(questLine);
//     var questHeader = document.createElement('h2');
//     questHeader.innerText = "Вопрос " + q;
//     questDiv.appendChild(questHeader);
//
//     var questTextInput = document.createElement("INPUT");
//     questTextInput.setAttribute("type", "text");
//     questTextInput.setAttribute("class", "input-xlarge");
//     questTextInput.setAttribute("placeholder", "Текст вопроса...");
//     // questTextInput.setAttribute("placeholder", "Quest text");
//     questTextInput.setAttribute("name", "quest_" + q + "_text");
//     questDiv.appendChild(questTextInput);
//
//     var p = document.createElement("p");
//     questDiv.appendChild(p);
//
//     var questCriteriaInput = document.createElement("INPUT");
//     criteriaNumIncrement();
//     questCriteriaInput.setAttribute("id", "quest_" + q + "_crit_" + c);
//     questCriteriaInput.setAttribute("type", "text");
//     // questCriteriaInput.setAttribute("placeholder", "Текст критерия");
//     questCriteriaInput.setAttribute("placeholder", "Criteria text");
//     questCriteriaInput.setAttribute("name", "quest_" + q + "_crit_" + c);
//     questDiv.appendChild(questCriteriaInput);
//
//     var p2 = document.createElement("p");
//     questDiv.appendChild(p2);
//
//     var questCriteriaAddButton = document.createElement("BUTTON");
//     questCriteriaAddButton.innerText = "Добавить критерий"
//     var divID = "quest_" + q;
//     questCriteriaAddButton.setAttribute("type", "button");
//     questCriteriaAddButton.setAttribute("onclick", "addCriteriaElement('" + divID + "')");
//     questDiv.appendChild(questCriteriaAddButton);
//
//     return questDiv;
//
//     document.getElementById("myForm").appendChild(questDiv);
//
// }


function addCriteriaElement(parentDivId) {
    var parent = document.getElementById(parentDivId);

    var bootstrapClass = "form-control input-xxlarge";
    var questCritClassName = parentDivId + "_criterion";
    var questCritNum = document.getElementsByClassName(bootstrapClass + " " + questCritClassName).length;
    questCritNum++;
    var questCritId = questCritClassName + questCritNum;

    var questCriteriaInput = document.createElement("INPUT");
    questCriteriaInput.setAttribute("id", questCritId);
    questCriteriaInput.setAttribute("type", "text");
    questCriteriaInput.setAttribute("placeholder", "Критерий оценки..");
    questCriteriaInput.setAttribute("class", bootstrapClass + " " + questCritClassName);
    questCriteriaInput.setAttribute("name", questCritId);

    parent.appendChild(document.createElement("p"));

    parent.appendChild(questCriteriaInput);

}


/*
 -----------------------------------------------------------------------------

 Functions that will be called upon, when user click on the Message textarea field.

 ------------------------------------------------------------------------------
 */
function textareaFunction() {
    var r = document.createElement('span');
    var y = document.createElement("TEXTAREA");
    var g = document.createElement("IMG");
    y.setAttribute("cols", "17");
    y.setAttribute("placeholder", "message..");
    g.setAttribute("src", "delete.png");
    increment();
    y.setAttribute("Name", "textelement_" + i);
    r.appendChild(y);
    g.setAttribute("onclick", "removeElement('myForm','id_" + i + "')");
    r.appendChild(g);
    r.setAttribute("id", "id_" + i);
    document.getElementById("myForm").appendChild(r);
}

