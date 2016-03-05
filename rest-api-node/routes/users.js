
module.exports  = function(app){
    var fk = require('../controllers/flipkart');//import

    //app.get('/musicians', musicians.findAll);
    app.get('/musicians/fk/:name', fk.findByNameInFk);

    //app.post('/musicians', musicians.add);
    //app.put('/musicians/:id', musicians.update);
    //app.delete('/musicians/:id', musicians.delete);
}


//var express = require('express');
//var router = express.Router();
//
///* GET users listing. */
//router.get('/', function(req, res, next) {
//  res.send('respond with a resource');
//});
//
//module.exports = router;
