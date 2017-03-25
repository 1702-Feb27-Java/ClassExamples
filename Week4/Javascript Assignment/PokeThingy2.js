$(function() { 
	//$('body').fadeOut(500).fadeIn(1000);
	
	$('#add').click(function() {
		if ($('#table').length == 0) {
			var table = "<table id='table' border=2></table>";
			$('#new').append(table);
		}
		var input = $('#input').val(); 
		$('#table').append('<tr><td>Input</td><td>' + input + '</td></tr>');
	});
	
	
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
	
	$('#sprites').append('<span class="center">Front:<br><img src="' + pokemon['sprites']['front_default'] + '\"></span><br>');
	$('#sprites').append('<span class="center">Back:<br><img src="' + pokemon['sprites']['back_default'] + '\"></span>');
	
}