var scriptManager = {
    createEvent: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'ScriptManager', // mapped to our native Java class called "ScriptManager"
            'callerEntry', // with this action name
            [{                  // and this array of custom arguments to create our entry
            }]
        );
     }
}
//module.exports = scriptManager;
