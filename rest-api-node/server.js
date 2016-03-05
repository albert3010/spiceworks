var express = require('express'),
    mongoose = require('mongoose'),
    fs = require('fs');
//
//var mongoUri = 'mongodb://localhost/noderest';
//mongoose.connect(mongoUri);
//
//var db = mongoose.connection;
//db.on('error', function () {
//    throw new Error('unable to connect to database at ' + mongoUri);
//});


var app=express();

//app.configure(function(){
//    app.use(express.bodyParser());
//});


app.get('/',function(req,res){
    res.send("This is sample test");
});
require('./models/musician');

require('./routes/index')(app);
require('./routes/users')(app);
app.listen(2001);

console.log("server started");

