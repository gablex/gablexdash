
function textCounter2(field, countfield, maxlimit)
{
//    alert('2');
    alert(document.getElementById("form:message").value);
     alert(document.getElementById("form:mumLen").value);
      alert(document.getElementById("form:remLen").value);
}
function textCounter(field, countfield, maxlimit) {
    alert('Kelvin');
if (field.value.length > 670)
field.value = field.value.substring(0, 670);
else
//countfield.value = maxlimit - field.value.length;
var smscount = 0;
if(field.value.length > 160)
{
    if(field.value.length <= 268)
        smscount = 2;
    else if(field.value.length <= 402)
        smscount = 3; 
    else if(field.value.length <= 562)
        smscount = 4;
    else if(field.value.length > 562)
        smscount = 5;
    else{
        
    }
}
else
{
    smscount = 1;
}
if(smscount < 2)
countfield.value = "("+(maxlimit - field.value.length)+"/"+maxlimit+")"+smscount;
else
countfield.value = "("+((134*smscount) - field.value.length)+"/"+134+")"+smscount;
}

function textCounterOld(field, countfield, maxlimit) {
if (field.value.length > 420)
field.value = field.value.substring(0, 420);
else
//countfield.value = maxlimit - field.value.length;
var smscount = 0;
if(field.value.length > 160)
{
    if(field.value.length <= 280)
        smscount = 2;
    if(field.value.length > 280)
        smscount = 3;
}
else
{
    smscount = 1;
}
if(smscount < 2)
countfield.value = "("+(maxlimit - field.value.length)+"/"+maxlimit+")"+smscount;
else
countfield.value = "("+((140*smscount) - field.value.length)+"/"+140+")"+smscount;
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

