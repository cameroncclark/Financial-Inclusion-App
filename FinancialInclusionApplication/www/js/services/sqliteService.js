fIApp.service("dbAccessor", function ($cordovaSQLite, $q) {
    var name = "";
    var location = "";
    var avatar = "";
    var dbService = this;

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
            dbService.selectUserDetails();
            dbService.updateTrophy("Updated Your Name");
        }, function (error) {
            console.error(error);
        });
    };

    // This will be a function to update the users location
    this.updateLocation = function (previousLocation, newLocation) {
        console.log("Entered the Update Location function: " + newLocation);
        var query = "UPDATE userData SET location = ? WHERE location = ?";
        $cordovaSQLite.execute(db, query, [newLocation, previousLocation]).then(function (result) {
            dbService.selectUserDetails();
            dbService.updateTrophy("Updated Your Location");
        }, function (error) {
            console.error(error);
        });
    };

    // This will be a function to update the users avatar
    this.updateAvatar = function (previousAvatar, newAvatar) {
        console.log("Entered the Update Avatar Function: " + newAvatar);
        var query = "UPDATE userData SET avatar = ? WHERE avatar = ?";
        $cordovaSQLite.execute(db, query, [newAvatar, previousAvatar]).then(function (result) {
            dbService.selectUserDetails();
            dbService.updateTrophy("Updated Your Picture");
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
                        trophyObject = {};
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

    this.getCategoryProgress = function (name) {
        var q = $q.defer();
        var returnCategory = [];
        var query = "SELECT name, percentageComplete FROM categories";
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            if (result.rows.length > 0) {
                for (var i = 0; i < result.rows.length; i++) {
                    var temp = {};
                    temp.name = result.rows.item(i).name;
                    temp.progress = result.rows.item(i).percentageComplete;
                    returnCategory.push(temp);
                }
                q.resolve(returnCategory);
            } else {
                console.error("NO ROWS EXIST IN categories");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    };

    this.getSubcatProgress = function (name) {
        var q = $q.defer();
        var returnSubCategory = [];
        var query = "SELECT name, percentageComplete FROM subcategories";
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            if (result.rows.length > 0) {
                for (var i = 0; i < result.rows.length; i++) {
                    var temp = {};
                    temp.name = result.rows.item(i).name;
                    temp.progress = result.rows.item(i).percentageComplete;
                    returnSubCategory.push(temp);
                }
                q.resolve(returnSubCategory);
            } else {
                console.error("NO ROWS EXIST IN subcategories");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    };

    /**
     * Whenever the user has reached the requirement of a new trophy, this function will unlock that trophy
     */
    this.updateTrophy = function (trophy) {
        var query = "UPDATE trophies SET acquired = 1 WHERE title LIKE '" + trophy + "'";
        console.log(query);
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            console.log("A Trophy Has Been Added - " + trophy);
        }, function (error) {
            console.error(error);
        });
        this.checkAllTrophies();
    };

    /**
     * This is to check if all trophies have been aquired
     */
    this.checkAllTrophies = function () {
        var checkQuery = "SELECT acquired FROM trophies WHERE acquired = 1";
        $cordovaSQLite.execute(db, checkQuery, []).then(function (result) {
            if (result.rows.length == 20) {
                dbService.updateTrophy("Unlock all the trophies");
            }
        }, function (error) {
            console.error(error);
        });
    };

    /**
     * This is to update calculators
     */
    this.updateCalculators = function (ID) {
        switch (ID) {
            case 1:
                dbService.updateTrophy("Performed a calculation on a calculator");
                $cordovaSQLite.execute(db, "UPDATE progress SET valueChanged = 'True' WHERE objective LIKE 'Perform calc " + ID + "'", []);
                dbService.checkAllCalculators();
                break;
            case 2:
                dbService.updateTrophy("Performed a calculation on a calculator");
                $cordovaSQLite.execute(db, "UPDATE progress SET valueChanged = 'True' WHERE objective LIKE 'Perform calc " + ID + "'", []);
                dbService.checkAllCalculators();
                break;
            case 3:
                dbService.updateTrophy("Performed a calculation on a calculator");
                $cordovaSQLite.execute(db, "UPDATE progress SET valueChanged = 'True' WHERE objective LIKE 'Perform calc " + ID + "'", []);
                dbService.checkAllCalculators();
                break;
        }
    };

    /**
     * This is to check if all calculators have been used
     */
    this.checkAllCalculators = function () {
        var checkQuery = "SELECT valueChanged FROM progress WHERE objective LIKE 'Perform calc%' AND valueChanged LIKE 'True'";
        $cordovaSQLite.execute(db, checkQuery, []).then(function (result) {
            console.log("COUNT = " + result.rows.length);
            if (result.rows.length == 3) {
                dbService.updateTrophy("Performed a calculation on all the calculators");
            }
        }, function (error) {
            console.error(error);
        });
    };

    /**
     * Called everytime a user flicks through a tip
     */
    this.incrementTipCount = function () {
        var tipCount = 0;
        $cordovaSQLite.execute(db, "SELECT counter FROM progress WHERE objective LIKE 'Tip Counter'", []).then(function (result) {
            if (result.rows.length > 0) {
                tipCount = result.rows.item(0).counter + 1;
                console.log("Tip Counter = " + tipCount);

                if (tipCount == 20) {
                    dbService.updateTrophy("Flicked through 20 hints");
                    // Unlock trophy when user reaches 20 hints
                } else if (tipCount == 50) {
                    dbService.updateTrophy("Flicked through 50 hints");
                    // Unlock trophy when user reaches 50 hints
                } else if (tipCount == 100) {
                    dbService.updateTrophy("Flicked through 100 hints");
                    // Unlock trophy when user reaches 100 hints
                }

                $cordovaSQLite.execute(db, "UPDATE progress SET counter = " + tipCount + " WHERE objective LIKE 'Tip Counter'", []);

            } else {
                console.log("ACCESSING PROGRESS FAILED AT incrementTipCount FUNCTION");
            }
        }, function (error) {
            console.error(error);
        });
    };

});
