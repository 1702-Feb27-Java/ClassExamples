//demoes jQuery's ready()
//$(document).ready(
//	function() {
//				
//	});

$(function() { //alternative way to specify ready()
	// for ready() section.
	$('body').fadeOut(500).fadeIn(1000);
	
	// for append(), val() section.
	$('#add').click(function() {
		if ($('#table').length == 0) {
			var table = "<table id='table' border=2></table>";
			$('#new').append(table);
		}
		var input = $('#input').val(); //takes in an argument, which is the setter of the value.
		$('#table').append('<tr><td>Input</td><td>' + input + '</td></tr>');
	});
	
	
	// for AJAX section.
	/*
	$('#ajax').click(function() {
		var xhtml = new XMLHttpRequest();
		xhtml.onreadystatechange = function() {
			if (xhtml.readyState == 4) {
				document.getElementById("ajax-response").innerHTML = this.response;
			}
		}
		xhtml.open("GET", "day20jquerypracticeajax.xml", true); //true is asynchronous request; false is synchronous.
		xhtml.send();
	});
	*/
	
	///* jQuery version
	$('#ajax').on('click', function() {
		$('#ajax-response').load("day20jquerypracticeajax.xml");
	});
	//*/
	
	// for pokeapi section.
	/*
	$('#pokeapi').on('click', function() {
		$.get("http://pokeapi.co/api/v2/pokemon/145", function(data, status) { //data is the response; status is the status code of the response.
		console.log(status);
		let s = '';
		for (let d in data) {
			s += d + ': [';
			if (d instanceof Array) {
				for (let i = 0; i < d.length; i++) {
					for (e in d[i]) {
						s += d[i][e] + ', ';
					}
				}
			}
			else {
				s += data[d] + ', ';
			}
			s += ']';
		}
		$('#pokeapi-box').text(s);
		});
	});
	*/
	
	
	$('#pokeapi').on('click', function() {
		let no = $('#pokemonno').val();
		$.get("http://pokeapi.co/api/v2/pokemon/" + no, function(data, status) {
			console.log(status);
			
			pokemon['name'] = data.name;
			pokemon['number'] = data.id;
			pokemon['types'] = data.types;
			pokemon['height'] = data.height;
			pokemon['weight'] = data.weight;
			pokemon['species'] = data.species;
			pokemon['sprites'] = data.sprites;
			
			loadPokemon();
		});
	});
	
});

var pokemon = {};
function loadPokemon() {
	/*
	let s = '';
	for (let p in pokemon) {
		s += p + ': ' + pokemon[p] + ', ';
	}
	for (let i in pokemon['sprites']) {
		s += pokemon['sprites'][i] + ' ';
	}
	$('#pokeapi-box').text(s);
	*/
	
	let table = '<table border=2>' +
		'<tr>' +
			'<td>Name</td>' +
			'<td id="name"></td>' +
		'</tr>' +
		'<tr>' +
			'<td>Number</td>' +
			'<td id="number"></td>' +
		'</tr>' +
		'<tr>' +
			'<td>Type</td>' +
			'<td id="types"></td>' +
		'</tr>' +
		'<tr>' +
			'<td>Height (m)</td>' +
			'<td id="height"></td>' +
		'</tr>' +
		'<tr>' +
			'<td>Weight (kg)</td>' +
			'<td id="weight"></td>' +
		'</tr>' +
		'<tr>' +
			'<td>Sprites</td>' +
			'<td id="sprites"></td>' +
		'</tr>' +
	'</table>';
	$('#pokeapi-box').empty();
	$('#pokeapi-box').append(table);
	
	$('#name').text(pokemon['name']);
	$('#number').text(pokemon['number']);
	$('#height').text(pokemon['height'] / 10);
	$('#weight').text(pokemon['weight'] / 10);
	
	let type = '';
	for (let i = 0; i < pokemon['types'].length; i++) {
		type += pokemon['types'][i]['type']['name'] + ' ';
	}
	console.log(type);
	$('#types').text(type);
	
	/*
	for (let s in pokemon['sprites']) {
		if (pokemon['sprites'][s] != null) {
			$('#sprites').append('<img src=\"' + pokemon['sprites'][s] + '\"><br>');
		}
	}
	*/
	
	$('#sprites').append('<span class="center">Front:<br><img src="' + pokemon['sprites']['front_default'] + '\"></span><br>');
	$('#sprites').append('<span class="center">Back:<br><img src="' + pokemon['sprites']['back_default'] + '\"></span>');
	
}