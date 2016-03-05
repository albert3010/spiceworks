var express = require('express');
var app = express();

/* GET home page. */

module.exports = function(app){
    app.get('/', function(req, res) {
        res.render('index', { title: 'Express' });
    });
}
