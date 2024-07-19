(() => {

function hideSpinner() {
	document.getElementById("delaySpinner").style.display="none";
}
function showSpinner() {
	document.getElementById("delaySpinner").style.display="block";
}

function sendMessage(message, enpointUrl) {
	var data = {
		text: message
	};
	showSpinner();
	setTimeout(() => {
		fetch(enpointUrl, {
			method:"POST",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(data, null, 2)
			
		})
		.then(response => {
			if(!response.ok) {
				throw new Error("Response was not ok " + response.statusText);
			}
			return response.json();
		})
		.then(json => {
			hideSpinner();
			showData(json);
		})
		.catch(error =>{
			hideSpinner();
			showData({
				"message" : error.message
			});
		});		
	}, 500);
}

function readNextMessage(enpointUrl) {
	showSpinner();
	setTimeout(()=>{
		fetch(enpointUrl)
		.then(response =>{
			if(!response.ok) {
				throw new Error("Response was not ok " + response.statusText);
			}
			return response.json();
		})
		.then(json => {
			hideSpinner();
			showData(json);
		})
		.catch(error =>{
			hideSpinner();
			showData({
				"message" : error.message
			});
		});
	}, 500);
}

function showData(json) {
	var out = "";
	out += "<h2>Returned</h2>";
	out += JSON.stringify(json);
	document.getElementById("resultContainer").innerHTML = out;
}
function clearData() {
	document.getElementById("resultContainer").innerHTML = "";
}

window.onload = ()=>{

	// Define global variables
	var sendEndPoint = document.getElementById("sendEndPointField").value;
	var readEndPoint = document.getElementById("readEndPointField").value;
	
	// Initialize the state here
	hideSpinner() ;
	
	//
	//Define event handlers
	//
	document.getElementById("messageForm").addEventListener("submit", function(event) {
		event.preventDefault();
		var message = document.getElementById("messageField").value;
		sendMessage(message, sendEndPoint);
	});
	document.getElementById("clearButton").addEventListener("click", function(event){
		event.preventDefault();
		clearData();
	});
	document.getElementById("readButton").addEventListener("click", function(event){
		event.preventDefault();
		readNextMessage(readEndPoint);
	});
};
		
})();
