<%--
  Created by IntelliJ IDEA.
  User: vidok
  Date: 22.10.17
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="appendCityModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">${modaltitle}</h4>
            </div>
            <div class="modal-body">
                <div class="input-group" style="width: 100%;">
                    <span class="label label-default">Наименование Города</span>
                    <input type="text"
                           class="form-control"
                           placeholder="Наименование города"
                           maxlength="32"
                           data-role="city-name-input"
                           data-required="true, pattern"
                           data-pattern="[A-Za-zА-Яа-я]{1,32}"
                           data-errmessage="Поле должно содержать буквы, длина строки от 1 до 32 !!!"
                    >
                </div>
                <div class="input-group" style="width: 100%;">
                    <span class="label label-default">Номер региона</span>
                    <select
                            class="form-control"
                            data-role="region-number-input"
                    >
                        ${regionsOptionsList}
                    </select>
                <%--<input--%>
                            <%--type="text"--%>
                            <%--class="form-control"--%>
                            <%--placeholder="Номер региона"--%>
                            <%--data-role="region-number-input"--%>
                            <%--data-required="true, pattern"--%>
                            <%--data-pattern="\d{1,32}"--%>
                            <%--data-errmessage="Поле должно содержать цифры, длина строки от 1 до 32 !!!"--%>
                    <%-->--%>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отменить</button>
                <button type="button" class="btn btn-primary" data-role="city-append">Подтвердить</button>
            </div>
        </div>
    </div>
</div>
