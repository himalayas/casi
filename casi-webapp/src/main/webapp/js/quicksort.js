/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12-8-15
 * Time: 上午9:16
 * To change this template use File | Settings | File Templates.
 */

var json_array = [
    {"Participants":"0", "Status":"Good", "Description":"good", "meetingTopic":"t-2", "State":"end", "meetingKey":"", "Hosts":"linzhou cisco"},
    {"Participants":"1000", "Status":"Poor", "Description":"1001", "meetingTopic":"t-8", "State":"In-progress", "meetingKey":"", "Hosts":"xiujun guo"},
    {"Participants":"200", "Status":"Poor", "Description":"1006", "meetingTopic":"k-7", "State":"In-progress", "meetingKey":"", "Hosts":"xiujun guo"},
    {"Participants":"3000", "Status":"Poor", "Description":"1003", "meetingTopic":"j-5", "State":"In-progress", "meetingKey":"", "Hosts":"xiujun guo"},
    {"Participants":"4", "Status":"Poor", "Description":"1001", "meetingTopic":"n-3", "State":"In-progress", "meetingKey":"", "Hosts":"xiujun guo"},
    {"Participants":"5", "Status":"Poor", "Description":"1009", "meetingTopic":"a-7", "State":"In-progress", "meetingKey":"", "Hosts":"xiujun guo"},
    {"Participants":"6", "Status":"Fair", "Description":"1001", "meetingTopic":"t-44", "State":"No-start", "meetingKey":"", "Hosts":"houman cisco"}
];

function quicksort(){
  var obj= json_array.sort(function(a, b) {
      a.Participants=parseInt(a.Participants);
      b.Participants=parseInt(b.Participants);
      return a.Participants < b.Participants ? 1 : -1;
  } );
    $.each(obj,function(n,item){
       document.write(item.Participants+",");
    });
}