function validateEmptyData(formData){
    let rtnVal = true;
    for (const formDataKey in formData) {
        if(formData[formDataKey]==''){
            rtnVal = false;
        }
    }
    if(!rtnVal){
        alert("You have empty field. Please fill in")
    }
    return rtnVal;
}