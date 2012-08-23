var findSchools = #{jsAction @LocationController.ajaxFindSchools(':term') /};
var addSchool = #{jsAction @LocationController.ajaxAddSchool() /};
var addSquad = #{jsAction @LocationController.ajaxAddSquad() /};
var findSquads = #{jsAction @LocationController.ajaxFindSquads(':term', ':schoolId') /};