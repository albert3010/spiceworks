var express = require('express');
var app = express();
//var path = require('path');

app.use(express.static(__dirname + '/views'));
//app.use(express.static(path.join(__dirname, '../views')));

app.get('/', function(req, res){
    res.send('hello world');
});
var app=express();

//app.configure(function(){
//    app.use(express.bodyParser());
//});


require('./routes/index')(app);
require('./routes/users')(app);
app.listen(80);
