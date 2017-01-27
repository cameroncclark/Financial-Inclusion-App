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
    this.updateName = function (previousName, newName){
        console.log("Entered the Update Name function: "+ newName);
        var query = "UPDATE userData SET name = ? WHERE name = ?";
        $cordovaSQLite.execute(db, query,[newName, previousName]).then(function(result){
            this.selectUserDetails();
        }, function (error){
            console.error(error);
        });
    };
    
    // This will be a function to update the users location
    this.updateLocation = function (location){
        console.log("Entered the Update Location function: " + location);
        var query = "UPDATE userData SET location = ?";
        $cordovaSQLite.execute(db, query,[location]).then(function(result){
            console.log(result.rows.item(0));
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

    // Check if data has been added correctly
    this.selectUserDetails = function () {
        console.log("Entered selectUserDetails");
        var response = {}; 
        var searchQuery = "SELECT * FROM userData";
        $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                console.log("SELECTED THE RIGHT THING -> " + result.rows.item(0).name + " " + result.rows.item(0).location);
                response.name = result.rows.item(0).name;
                response.location = result.rows.item(0).location;
                console.log(response);
                return response;
            } else {
                console.log("NO ROWS EXIST");
            }
        }, function (error) {
            console.error(error);
        });

    };



})
