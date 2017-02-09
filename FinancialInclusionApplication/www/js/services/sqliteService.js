fIApp.service("dbAccessor", function ($cordovaSQLite, $q) {
    var name = "";
    var location = "";
    var avatar = "";


    this.setName = function (userName) {
        name = userName;
    }

    this.getName = function () {
        return name;
    }

    this.setLocation = function (userLocation) {
        location = userLocation
    }

    this.getLocation = function () {
        return location;
    }

    this.setAvatar = function (userAvatar) {
        avatar = userAvatar;
    }

    this.getAvatar = function () {
        return avatar;
    }

    this.testPrint = function () {
        console.log("Testing print method");
    }

    this.testReturn = function () {
        return "Testing the return method";
    }

    // This will be a function to update the users name
    this.updateName = function (previousName, newName) {
        console.log("Entered the Update Name function: " + newName);
        var query = "UPDATE userData SET name = ? WHERE name = ?";
        $cordovaSQLite.execute(db, query, [newName, previousName]).then(function (result) {
            this.selectUserDetails();
        }, function (error) {
            console.error(error);
        });
    };

    // This will be a function to update the users location
    this.updateLocation = function (previousLocation, newLocation) {
        console.log("Entered the Update Location function: " + newLocation);
        var query = "UPDATE userData SET location = ? WHERE location = ?";
        $cordovaSQLite.execute(db, query, [newLocation, previousLocation]).then(function (result) {
            this.selectUserDetails();
        }, function (error) {
            console.error(error);
        });
    };

    // This will be a function to update the users avatar
    this.updateAvatar = function (previousAvatar, newAvatar) {
        console.log("Entered the Update Avatar Function: " + newAvatar);
        var query = "UPDATE userData SET avatar = ? WHERE avatar = ?";
        $cordovaSQLite.execute(db, query, [newAvatar, previousAvatar]).then(function (result) {
            this.selectUserDetails();
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
                console.log("SELECTED THE RIGHT THING -> " + result.rows.item(0).name + " " + result.rows.item(0).location + " " + result.rows.item(0).avatar);
                response.name = result.rows.item(0).name;
                response.location = result.rows.item(0).location;
                response.avatar = result.rows.item(0).avatar;
                console.log(response);
                return response;
            } else {
                console.log("NO ROWS EXIST");
            }
        }, function (error) {
            console.error(error);
        });

    };

    this.returnString = function () {
        console.log("In test function");
        var test = [[{ name: "hi" }, { name: "obb" }], [{ name: "hi" }, { name: "obb" }]];
        return test;
    };

    this.loadTrophyData = function () {
        console.log("Entered loadTrophyData");
        var responses = [];
        var trophyObject = {};
        var items;
        var searchQuery = "SELECT * FROM trophies";
        return $cordovaSQLite.execute(db, searchQuery).then(function (result) {
            if (result.rows.length > 0) {
                for (i = 0; i < 21; i++) {
                    trophyObject.title = result.rows.item(i).title;
                    trophyObject.image = result.rows.item(i).image;
                    trophyObject.description = result.rows.item(i).description;
                    trophyObject.hint = result.rows.item(i).hint;
                    trophyObject.acquired = result.rows.item(i).acquired;
                    responses[i] = trophyObject;
                }

                return responses;
            } else {
                console.log("NO ROWS EXIST");
            }
        }, function (error) {
            console.error(error);
        });
    };

    this.loadTrophyTable = function () {
        var q = $q.defer(); //THIS IS NEEDED TO RESPOND WHEN RESOLVE IS READY
        var table = [];
        var row = [];
        var trophyObject = {};
        var searchQuery = "SELECT * FROM trophies";
        $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                trophyID = 0;
                for (i = 0; i < 7; i++) {
                    for (j = 0; j < 3; j++) {
                        trophyObject.id = result.rows.item(trophyID).id;
                        trophyObject.title = result.rows.item(trophyID).title;
                        trophyObject.image = result.rows.item(trophyID).image;
                        trophyObject.description = result.rows.item(trophyID).description;
                        trophyObject.hint = result.rows.item(trophyID).hint;
                        trophyObject.acquired = result.rows.item(trophyID).acquired;
                        row[j] = trophyObject;
                        trophyID++;
                        trophyObject= {};
                    }
                    table[i] = row;
                    row = [];
                }  
                q.resolve(table); //ONCE DATA IS READY TO BE RETURNED, THIS WILL CALL THE .then FUNCTION ON THE CALL 
                
            } else {
                console.log("NO ROWS EXIST IN trophies");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise; //RETURNING THE PROMISED DATA
    };

});
