$(function(){
	
	$('#inputSelectSchool').keyup(function(){
		$.ajax(
				findSchools({"term": $(this).val()}))
				.success(function(data){
					$('#divShowSchools select').html(data);
					$('#divShowSquads select').html('')
				})
				.fail(function(data){
					$('#school span.error').html(data.responseText);
				})
	})
	
	$('#linkAddSchool').click(function(){
		console.log($('#inputSelectSchool').val());
		$.ajax({
				url: addSchool(), 
				data: {"school.name": $('#inputSelectSchool').val()}
		})
					.success(function(data){
						$('#divShowSchools select').html(data)
						$('#divShowSchools select').change();
					})
					.fail(function(data){
						$('#school span.error').html(data.responseText);
					})
				
	})
	
	$('#divShowSchools select').change(function(){
		console.log('blubbi');
		console.log($('#divShowSchools select').attr('value'));
		$.ajax(
				findSquads({
					"term": $('#inputSelectSquad').val(),
					"schoolId": $('#divShowSchools select').attr('value')
					}))
					.success(function(data){
						$('#divShowSquads select').html(data)
					})
					.fail(function(data){
							$('#squad span.error').html(data.responseText);
					})
	})
	
	$('#linkAddSquad').click(function(){
		$.ajax({
				url: addSquad(),
				data: {
					"squad.name": $('#inputSelectSquad').val(), 
					"squad.school.id": $('#divShowSchools select').attr('value')
				}
			})
					.success(function(data){
						$('#divShowSquads select').html(data)
					})
					.fail(function(data){
						$('#squad span.error').html(data.responseText);
					})
				
	})
	
	$('#inputSelectSquad').keyup(function(){
		console.log($('#divShowSchools select').attr('value'));
		$.ajax(
				findSquads({
					"term": $(this).val(), 
					"schoolId": $('#divShowSchools select').attr('value')
						}))
				.success(function(data){
					$('#divShowSquads select').html(data)
				})
				.fail(function(data){
					$('#squad span.error').html(data.responseText);
				})
	})
	
	
})
