/* eslint-disable */ 
// The Cloud Functions for Firebase SDK to create Cloud Functions and set up triggers.
const functions = require('firebase-functions');
const admin = require('firebase-admin');
const { ref } = require('firebase-functions/v1/database');

// The Firebase Admin SDK to access Firestore.
admin.initializeApp();

exports.updateDatabase = functions.pubsub.schedule('every 1 minutes').onRun((context) => {
    return admin.storage().bucket().getFiles().then(files => {
        return admin.database().ref('number/').once('value', function(snapshot){
            const targetNumber = parseInt(snapshot.val()) + 1;
            var topQuiz = Infinity;
            var topFileName = "quiz_0.json";
            files[0].forEach(file =>{
                var fileName = file.name;
                if(fileName.includes('quiz')){
                    fileName.replace('.json', '');
                    fileNumber = parseInt(fileName.split("_").pop());
                    if((fileNumber >= targetNumber) && (fileNumber < topQuiz)){
                        topQuiz = fileNumber;
                        topFileName = file.name;
                    }
                }
            });
    
            return admin.storage().bucket().file(topFileName).download().then((data) => {
                admin.database().ref().remove();
                const jsonObj = JSON.parse(data.toString('utf8'));
                TransferJSON(jsonObj, null);
            });
        });
    });
});

function TransferJSON(object, ref){
    for (const [key, value] of Object.entries(object)) {
        if(value === Object(value)){ 
            TransferJSON(value, (ref ? ref : "") + key + "/");
        }
        else{
            admin.database().ref((ref ? ref : "") + key + "/").set(value);
        }
    }
}
/* eslint-enable */
