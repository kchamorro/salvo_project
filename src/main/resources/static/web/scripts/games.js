
function getGames(){

	
	fetch("/api/games")
		.then(res => res.json())
		.then(json => {
			data = json
		    app.games = json.games
		    app.player = json.player
		})

}

getGames()

var app = new Vue({
    el: "#app",
    data: {
        games: [],
        players: [],
                username: "",
                password: "",
    },
    methods: {
    	changeDateFormat (dateString){
		    return new Date(dateString).toLocaleString();
		},
		login(evt) {
		   evt.preventDefault(); 

		   let formData = new FormData(evt.target)
		   

		   fetch('/api/login',{
				method: 'POST',
				body: formData,
		  		
			})
			.then((res)=> res)
			.then(json =>{
				console.log(json)
				getGames()
			})
			.catch((error)=> console.log(error))

		},
		logout(){
			fetch('/api/logout').then(() => getGames())
		},
		createGame(){
			fetch('/api/games',{
				method: 'POST'
			})
			.then(res => {
				if(res.ok){
					return res.json()
				}else{
					return Promise.reject(res.json())
				}
			})
			.then(json => {
	
				location.href = '/web/game.html?gp=' + json.gpId
			})
			.catch(error => error)
			.then(error => console.log(error))
		},
		joinGame(gameId){
			fetch('/api/games/' + gameId + '/players', {
				method: 'POST'
			})
			.then(res => {
				if(res.ok){
					return res.json()
				}else{
					return Promise.reject(res.json())
				}
			})
			.then(json => {
				console.log(json)
				location.href = "/web/game.html?gp=" + json.gpId
			})
			.catch(error => error)
			.then(error => console.log(error))
		},
		register() {
                    if (this.username.trim() == "" || this.password.trim() == "") {
                        alert("username or password empty");
                        app.emptyTextInputs();
                        return;
                    }

                    fetch("/api/players", {
                            credentials: 'include',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded'
                            },
                            method: 'POST',
                            body: 'username=' + this.username + '&password=' + this.password,
                        })
                        .then(function (res) {
                            return res.json();
                        }).then(function (data) {
                            console.log('Request success: ', data);
                            if (data.username) {
                                alert("Welcome " + app.username);
                                app.loginForm = false;
                                app.logoutForm = true;
                                app.usernameDisplay = true;
                                app.logIn();
                            } else {
                                alert("Wrong username or password, try again");
                            }
                        })
                        .catch(function (error) {
                            console.log('Request failure: ', error);
                        });
        }
    }
});









