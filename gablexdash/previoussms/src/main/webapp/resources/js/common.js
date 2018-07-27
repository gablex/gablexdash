
function textCounter() {
if (document.getElementById("form:message").value.length > 670)
document.getElementById("form:message").value = document.getElementById("form:message").value.substring(0, 670);
else
//document.getElementById("form:remLen").value = maxlimit - document.getElementById("form:message").value.length;
var smscount = 0;
if(document.getElementById("form:message").value.length > 160)
{
    if(document.getElementById("form:message").value.length <= 268)
        smscount = 2;
    else if(document.getElementById("form:message").value.length <= 402)
        smscount = 3;
     else if(document.getElementById("form:message").value.length <= 562)
        smscount = 4;
    else if(document.getElementById("form:message").value.length > 562)
        smscount = 5;
}
else
{
    smscount = 1;
}
if(smscount < 2)
document.getElementById("form:remLen").value = "("+(document.getElementById("form:mumLen").value- document.getElementById("form:message").value.length)+"/"+document.getElementById("form:mumLen").value+")"+smscount;
else
document.getElementById("form:remLen").value = "("+((134*smscount) - document.getElementById("form:message").value.length)+"/"+134+")"+smscount;
}

function textCounterOld(field, countfield, maxlimit) {
if (document.getElementById("form:message").value.length > 420)
document.getElementById("form:message").value= document.getElementById("form:message").value.substring(0, 420);
else
//document.getElementById("form:message").value = maxlimit - document.getElementById("form:message").value.length;
var smscount = 0;
if(document.getElementById("form:message").value.length > 160)
{
    if(document.getElementById("form:message").value.length <= 280)
        smscount = 2;
    if(document.getElementById("form:message").value.length > 280)
        smscount = 3;
}
else
{
    smscount = 1;
}
if(smscount < 2)
document.getElementById("form:remLen").value = "("+(document.getElementById("form:mumLen").value- document.getElementById("form:message").value.length)+"/"+document.getElementById("form:mumLen").value+")"+smscount;
else
document.getElementById("form:remLen").value = "("+((140*smscount) - document.getElementById("form:message").value.length)+"/"+140+")"+smscount;
}











function makeValue() {
document.form.to.value=document.form.to.value  + document.form.names.value + '\n';
}

function checkFromAddr() {
    var fromAddr = document.form.from.value;    
    if(fromAddr.length == 0 || fromAddr == '0')
    {
        alert("Please select SMS from Address."); return false;
    }
}

function OnChange() {
if (document.form.contacts.value=='groups') {
        for(i = document.form.names.length - 1; i > 0; i--) { 
document.form.names.options[i]    = null; 
}
document.form.names.options[0] = new Option('--Select One--','');
}
else if (document.form.contacts.value=='contacts'){
        for(i = document.form.names.length - 1; i > 0; i--) {
document.form.names.options[i]    = null; 
}
document.form.names.options[0] = new Option('--Select One--','');
}
else {
document.form.names.options[0] = new Option('--Select One--','');
}
}

function loadcontact() {
//alert(PF('selectWV').getSelectedValue());
var kevol =PF('selectWV').getSelectedValue();
var kevol2 =PF('youtlink').jq.val();
PF('youtlink').jq.val(kevol+"\n"+kevol2);
}


function loadgroup() {
//alert(PF('selectWV').getSelectedValue());
var kevol =PF('selectWV2').getSelectedValue();
var kevol2 =PF('youtlink').jq.val();
PF('youtlink').jq.val(kevol+kevol2);
}
