
// function removeElement(elementId) {
//     var element = document.getElementById(elementId);
//     element.outerHTML = '';
// }

function removeElement(element) {
    $(element).outerHTML = '';
}

function removeThisQuestion(element){

    $(element).closest('.question').remove();
    generateId();
}



function removeQuestionBlock(variantId) {
    var lastQuestNum = document.getElementsByClassName(variantId + "_question").length;
    removeElement(variantId + "_question" + lastQuestNum);
}

function removeCriteriaElement(element) {

    var questionDivElement = $(element).closest('.question').first();
    var bootstrapPostfix = "form-control";
    var criterionClass = "criterion";
    var lastCritNum = document.getElementsByClassName(criterionClass + " " + bootstrapPostfix).length;
    document.getElementsByClassName(criterionClass + " " + bootstrapPostfix)[lastCritNum-1].remove();
}

//function generateID(parentDivId) {
function generateId() {
    var variantsTabDiv = document.getElementById('variantTabContent');

    var variantClassDiv = "tab-pane";
    for (var v = 1; v <= document.getElementsByClassName(variantClassDiv).length; v++) {

        var variantDiv = document.getElementsByClassName(variantClassDiv)[v-1];
        var variantId = "variant" + v;
        variantDiv.setAttribute("id", variantId);
        variantDiv.setAttribute("name", variantId);

        var questionClass = "question";
        for (var q = 1; q <= variantDiv.getElementsByClassName(questionClass).length; q++) {
            var questionDiv = variantDiv.getElementsByClassName(questionClass)[q-1];
            var questionId = questionClass + q;
            var questionDivId = variantId + "_" + questionId;
            questionDiv.setAttribute("id", questionDivId);
            questionDiv.setAttribute("name", questionDivId);

            questionDiv.getElementsByTagName('h2')[0].innerText = "Вопрос " + q;
            questionDiv.getElementsByTagName('input')[0].setAttribute("name", questionDivId + "_text");
            questionDiv.getElementsByTagName('textarea')[0].setAttribute("name", questionDivId + "_answer");

            //var bootsrapPostfix = "form-control";
            var criterionClass = "criterion";
            for (var c = 1; c <= questionDiv.getElementsByClassName(criterionClass).length; c++) {
                var criterionDiv = questionDiv.getElementsByClassName(criterionClass)[c-1];
                var criterionId = criterionClass + c;
                var criterionDivId = questionDivId + "_" + criterionId;

                criterionDiv.setAttribute("id", criterionDivId);
                criterionDiv.setAttribute("name", criterionDivId);
            }
        }
    }
}

function addQuestionBlockToTab(element){

    var questDiv = createQuestDivFunction(element);
}


// function addQuestionBlock() {
//     var questDiv = createQuestDivFunction();
//     document.getElementById("myForm").appendChild(questDiv);
// }

function addQuestionBlock(elementButton) {
    var variantTabDiv = $(elementButton).parent()[0];

    var questDiv = createQuestDivFunction(variantTabDiv);

    // document.getElementById(parentDivId).appendChild(questDiv);
}

function createQuestDivFunction(variantTabDiv) {

    var questDiv = document.createElement('DIV');
    var questClassName = "question";
    var tabQuestNum = variantTabDiv.getElementsByClassName(questClassName).length;
    var questId = questClassName + ++tabQuestNum;

    questDiv.setAttribute("id", questId);
    questDiv.setAttribute("class", questClassName);

    var questLine = document.createElement('HR');
    questDiv.appendChild(questLine);

    var questRemoveButton = document.createElement("BUTTON");
    questRemoveButton.innerHTML = '<span aria-hidden="true" class="glyphicon glyphicon-remove" style="color:red;"/>';
    questRemoveButton.setAttribute("type", "button");
    questRemoveButton.setAttribute("class", "btn btn-default btn-sm");
    questRemoveButton.setAttribute("onclick", "removeThisQuestion(this)");
    questRemoveButton.setAttribute("title", "Удалить вопрос");
    questDiv.appendChild(questRemoveButton);

    var questHeader = document.createElement('h2');
    questHeader.innerText = 'Вопрос ' + tabQuestNum;
    questDiv.appendChild(questHeader);

    var questTextInput = document.createElement("INPUT");
    questTextInput.setAttribute("type", "text");
    questTextInput.setAttribute("class", "form-control input-xxlarge");
    questTextInput.setAttribute("placeholder", "Текст вопроса...");
    questTextInput.setAttribute("required", "");
    questDiv.appendChild(questTextInput);

    questDiv.appendChild(document.createElement("p"));

    var questAnswerInput = document.createElement("TEXTAREA");
    questAnswerInput.setAttribute("type", "text");
    questAnswerInput.setAttribute("class", "form-control input-xxlarge");
    questAnswerInput.setAttribute("placeholder", "Текст ответа...");
    questAnswerInput.setAttribute("required", "");
    questDiv.appendChild(questAnswerInput);

    questDiv.appendChild(document.createElement("p"));

    var questCriteriaLabel = document.createElement("LABEL");
    questCriteriaLabel.innerText = "Критерии оценки ";
    questDiv.appendChild(questCriteriaLabel);

    var questCriteriaAddButton = document.createElement("BUTTON");
    questCriteriaAddButton.innerHTML = '<span aria-hidden="true" class="glyphicon glyphicon-plus" style="color:green;"/>';
    // questCriteriaAddButton.innerText = "✚";
    questCriteriaAddButton.setAttribute("type", "button");
    questCriteriaAddButton.setAttribute("class", "btn btn-default btn-sm");
    questCriteriaAddButton.setAttribute("onclick", "addCriteriaElement(this)");
    questCriteriaAddButton.setAttribute("title", "Добавить критерий");
    questCriteriaLabel.appendChild(questCriteriaAddButton);

    var questCriteriaRemoveButton = document.createElement("BUTTON");
    questCriteriaRemoveButton.innerHTML = '<span aria-hidden="true" class="glyphicon glyphicon-minus" style="color:red;"/>';
    // questCriteriaRemoveButton.innerText = "—";
    questCriteriaRemoveButton.setAttribute("type", "button");
    questCriteriaRemoveButton.setAttribute("class", "btn btn-default btn-sm");
    questCriteriaRemoveButton.setAttribute("onclick", "removeCriteriaElement(this)");
    questCriteriaRemoveButton.setAttribute("title", "Удалить критерий");
    questCriteriaLabel.appendChild(questCriteriaRemoveButton);

    variantTabDiv.appendChild(questDiv);
    //variantTabDiv.insertBefore(questDiv, variantTabDiv.firstChild)
}



function addCriteriaElement(elementButton) {

    var questDivElement = $(elementButton).parent().parent()[0];

    var bootstrapPostfix = "form-control";
    var questCritClassName = "criterion";
    var questCritNum = document.getElementsByClassName(questCritClassName + " " + bootstrapPostfix).length;
    questCritNum++;

    var questCriteriaInput = document.createElement("INPUT");
    questCriteriaInput.setAttribute("type", "text");
    questCriteriaInput.setAttribute("placeholder", "Критерий оценки..");
    questCriteriaInput.setAttribute("class", questCritClassName + " " + bootstrapPostfix);
    questCriteriaInput.setAttribute("required", "");

    questDivElement.appendChild(document.createElement("p"));

    questDivElement.appendChild(questCriteriaInput);

}


//старый вариант с посдчетом ID и name'ов....
// function createQuestDivFunction(parentDivId) {
//
//     var questDiv = document.createElement('DIV');
//     var questClassName = parentDivId + "_question";
//     var tabQuestNum = document.getElementsByClassName(questClassName).length;
//     var questId = questClassName + ++tabQuestNum;
//     //tabQuestNum++;
//     questDiv.setAttribute("id", questId);
//     questDiv.setAttribute("class", questClassName);
//
//     var questLine = document.createElement('HR');
//     questDiv.appendChild(questLine);
//     var questHeader = document.createElement('h2');
//     // questHeader.setAttribute("style", "text-align: left");
//     //questHeader.innerText = "Вопрос " + tabQuestNum;
//     //questHeader.innerText = "Вопрос";
//     questHeader.innerHTML = 'Вопрос ' + tabQuestNum +
//         '<button class="btm btn-danger btn-sm" onclick="removeThisQuestion(this)" type="button" title="Удалить вопрос">—</button>';
//     questDiv.appendChild(questHeader);
//
//     var questTextInput = document.createElement("INPUT");
//     questTextInput.setAttribute("type", "text");
//     questTextInput.setAttribute("class", "form-control input-xxlarge");
//     questTextInput.setAttribute("placeholder", "Текст вопроса...");
//     questTextInput.setAttribute("name", questId + "_text");
//     questDiv.appendChild(questTextInput);
//
//     questDiv.appendChild(document.createElement("p"));
//
//     var questAnswerInput = document.createElement("TEXTAREA");
//     questAnswerInput.setAttribute("type", "text");
//     questAnswerInput.setAttribute("class", "form-control input-xxlarge");
//     questAnswerInput.setAttribute("placeholder", "Текст ответа...");
//     questAnswerInput.setAttribute("name", questId + "_answer");
//     questDiv.appendChild(questAnswerInput);
//
//     //var p2 = document.createElement("p");
//     questDiv.appendChild(document.createElement("p"));
//
//     var questCriteriaLabel = document.createElement("LABEL");
//     questCriteriaLabel.innerText = "Критерии оценки ";
//     questDiv.appendChild(questCriteriaLabel);
//
//     var questCriteriaAddButton = document.createElement("BUTTON");
//     questCriteriaAddButton.innerHTML = '<span aria-hidden="true" class="glyphicon glyphicon-plus-sign" style="color:green;"/>';
//     // questCriteriaAddButton.innerText = "✚";
//     questCriteriaAddButton.setAttribute("type", "button");
//     questCriteriaAddButton.setAttribute("class", "btn btn-link");
//     questCriteriaAddButton.setAttribute("onclick", "addCriteriaElement(this)");
//     // questCriteriaAddButton.setAttribute("onclick", "addCriteriaElement('" + questId + "')");
//     questCriteriaLabel.appendChild(questCriteriaAddButton);
//
//     var questCriteriaRemoveButton = document.createElement("BUTTON");
//     questCriteriaRemoveButton.innerText = "—";
//     questCriteriaRemoveButton.setAttribute("type", "button");
//     questCriteriaRemoveButton.setAttribute("class", "btn btn-xs btn-danger");
//     questCriteriaRemoveButton.setAttribute("onclick", "removeCriteriaElement(this)");
//     questCriteriaLabel.appendChild(questCriteriaRemoveButton);
//
//     return questDiv;
//
// }
