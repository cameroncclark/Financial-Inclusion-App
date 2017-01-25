fIApp.service("dbAccessor", function ($cordovaSQLite) {
    var name = "";
    var location = "";

    this.setName = function (userName) {
        name = userName;
    }

    this.getName = function () {
        return name;
    }

    this.setLocation = function (userLocation) {
        location = userLocation
    }

    this.getLocation = function (){
        return location;
    }

    this.testPrint = function () {
        console.log("Testing print method");
    }

    this.testReturn = function () {
        return "Testing the return method";
    }

    // This will be a function to update the users name
    this.updateName = function (name){
        console.log("Entered the Update Name function");
        var query = "UPDATE userData SET name = 'name'";
        $cordovaSQLite.execute(db, query).then(function(result){
            console.log("UPDATED NAME");
        }, function (error){
            console.error(error);
        });
    };

    this.insertName = function (name) {
        console.log("in the insert function");
        var query = "INSERT INTO userData (firstname, lastname) VALUES (?,?)";
        console.log("made query");
        $cordovaSQLite.execute(db, query, [firstname, lastname]).then(function (result) {
            console.log("INSERT ID -> " + result.insertId);
        }, function (error) {
            console.error(error);
        });

    };

    this.selectUserDetails = function () {
        var response = {}; 
        var query = "SELECT name, location FROM userData";
        $cordovaSQLite.execute(db, query).then(function (result) {
            if (result.rows.length > 0) {
                response.name = result.rows.item(0).name;
                response.location = result.rows.item(0).location;
                console.log(response);
            } else {
                console.log("NO ROWS EXIST");
            }
        }, function (error) {
            console.error(error);
        });
    };



})
