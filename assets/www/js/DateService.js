/**
 * Created by IntelliJ IDEA.
 * User: hakangleissman
 * Date: 2014-02-04
 * Time: 07:44
 * To change this template use File | Settings | File Templates.
 */
function DateService(){
    this.dateString = '2014-01-01';
}

DateService.prototype.setCurrentDate = function(date){
    this.dateString = date;
}

DateService.prototype.getCurrentDate = function(){
    return this.dateString;
}
