$(document).ready(function(){

var myDB;
//Open Database Connection
document.addEventListener("deviceready",onDeviceReady,false);
function onDeviceReady(){
myDB = window.sqlitePlugin.openDatabase({name: "mySQLite.db", location: 'default'});
}
//Create new table
$("#createTable").click(function(){
    myDB.transaction(function(transaction) {
    transaction.executeSql('CREATE TABLE IF NOT EXISTS DBgap (id integer primary key, name text, location text, date text, time text, organizer text, report text)', [],
        function(tx, result) {
            alert("Table created successfully");
        }, 
        function(error) {
              alert("Error occurred while creating the table.");
        });
    });
});

//Insert New Data
$("#insert").click(function(){
  var event_name=$("#name").val();
  var event_location=$("#location").val();
   var event_date=$("#date").val();
    var event_time=$("#time").val();
     var event_organizer=$("#organizer").val();
      var event_report=$("#report").val();
  myDB.transaction(function(transaction) {
        var executeQuery = "INSERT INTO DBgap (name, location,date, time, organizer, report) VALUES (?,?,?,?,?,?)";
        transaction.executeSql(executeQuery, [event_name,event_location,event_date,event_time,event_organizer,event_report]
            , function(tx, result) {
                 alert('Inserted');
            },
            function(error){
                alert('Error occurred');
            });
    });
});


//Display Table Data
$("#showTable").click(function(){
  $("#TableData").html("");
  myDB.transaction(function(transaction) {
  transaction.executeSql('SELECT * FROM DBgap', [], function (tx, results) {
       var len = results.rows.length, i;
       $("#rowCount").html(len);
       for (i = 0; i < len; i++){
          $("#TableData").append("<tr><td>"+results.rows.item(i).id+"</td><td>"+results.rows.item(i).name+"</td><td>"+results.rows.item(i).report+"</td><td><a href='edit.html?id="+results.rows.item(i).id+"&Name="+results.rows.item(i).name+"&report="+results.rows.item(i).report+"'>Edit</a> &nbsp;&nbsp; <a class='delete' href='#' id='"+results.rows.item(i).id+"'>Delete</a></td></tr>");
       }
    }, null);
  });
});

//Delete Data from Database
$(document.body).on('click', '.delete' ,function(){
  var id=this.id;
  myDB.transaction(function(transaction) {
    var executeQuery = "DELETE FROM DBgap where id=?";
    transaction.executeSql(executeQuery, [id],
      //On Success
      function(tx, result) {alert('Delete successfully');},
      //On Error
      function(error){alert('Something went Wrong');});
  });
});



$("#update").click(function(){
  var id=$("#id").text();
 var event_name=$("#name").val();
   var event_location=$("#location").val();
    var event_date=$("#date").val();
     var event_time=$("#time").val();
      var event_organizer=$("#organizer").val();
       var event_report=$("#report").val();
  myDB.transaction(function(transaction) {
    var executeQuery = "UPDATE DBgap SET name=?, location=? , date=?, time=?, organizer=?, report=?, WHERE id=?";
    transaction.executeSql(executeQuery, [event_name,event_location,event_date,event_time,event_organizer,event_report,id],
      //On Success
      function(tx, result) {alert('Updated successfully');},
      //On Error
      function(error){alert('Something went Wrong');});
  });
});
$("#DropTable").click(function(){
    myDB.transaction(function(transaction) {
        var executeQuery = "DROP TABLE  IF EXISTS DBgap";
        transaction.executeSql(executeQuery, [],
            function(tx, result) {alert('Table deleted successfully.');},
            function(error){alert('Error occurred while droping the table.');}
        );
    });
});
});


