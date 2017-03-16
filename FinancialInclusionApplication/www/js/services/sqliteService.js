fIApp.service("dbAccessor", function ($cordovaSQLite, $q, $rootScope, $http) {
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

    /**
     * This updates the users name
     */
    this.updateName = function (previousName, newName) {
        var query = "UPDATE userData SET name = ? WHERE name = ?";
        $cordovaSQLite.execute(db, query, [newName, previousName]).then(function (result) {
            dbService.selectUserDetails();
            dbService.updateTrophy("Updated Your Name");
        }, function (error) {
            console.error(error);
        });
    };

    /**
     * This updates the users location
     */
    this.updateLocation = function (previousLocation, newLocation) {
        var query = "UPDATE userData SET location = ? WHERE location = ?";
        $cordovaSQLite.execute(db, query, [newLocation, previousLocation]).then(function (result) {
            dbService.selectUserDetails();
            dbService.updateTrophy("Updated Your Location");
        }, function (error) {
            console.error(error);
        });
    };

    /**
     * This updates the users avatar
     */
    this.updateAvatar = function (previousAvatar, newAvatar) {
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
        var response = {};
        var searchQuery = "SELECT * FROM userData";
        $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                response.name = result.rows.item(0).name;
                response.location = result.rows.item(0).location;
                response.avatar = result.rows.item(0).avatar;
                return response;
            } else {
                console.error("NO ROWS EXIST IN TABLE - userData");
            }
        }, function (error) {
            console.error(error);
        });

    };

    // IS THIS STILL USED
    this.returnString = function () {
        var test = [[{ name: "hi" }, { name: "obb" }], [{ name: "hi" }, { name: "obb" }]];
        return test;
    };

    /**
     * This loads in the trophy table. 
     * 
     * It uses 'promises' to ensure the data is correctly passed through.
     */
    this.loadTrophyTable = function () {
        var q = $q.defer();
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
                q.resolve(table);
            } else {
                console.error("NO ROWS EXIST IN TABLE - trophies");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    };

    /**
     * This is to get the categories progress to display to the screen.
     */
    this.getCategoryProgress = function () {
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
                console.error("NO ROWS EXIST IN TABLE - categories");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    };

    /**
     * This is to get the subcategories progress to display to the screen.
     */
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
                console.error("NO ROWS EXIST IN TABLE - subcategories");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    };

    /**
     * This is to reset all progress in the application
     */
    this.clearAllProgress = function () {
        $cordovaSQLite.execute(db, "UPDATE trophies SET acquired = 0", []);
        $cordovaSQLite.execute(db, "UPDATE progress SET counter = 0", []);
        $cordovaSQLite.execute(db, "UPDATE progress SET valueChanged = 'false'", []);
        $cordovaSQLite.execute(db, "UPDATE categories SET percentageComplete = 0", []);
        $cordovaSQLite.execute(db, "UPDATE subcategories SET percentageComplete = 0", []);
        $cordovaSQLite.execute(db, "UPDATE userData SET name = 'Your Name Here', location = 'Your Location Here', avatar = 'img/startImage.png'", []);
    };

    /**
     * Whenever the user has reached the requirement of a new trophy, this function will unlock that trophy
     */
    this.updateTrophy = function (trophy) {
        var query = "UPDATE trophies SET acquired = 1 WHERE title LIKE '" + trophy + "'";
        console.log(query);
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            console.log("TROPHY HAS BEEN ADDED - " + trophy);
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
     * This is to update trophies based off websites being loaded
     */
    this.loadingWebsite = function () {
        dbService.updateTrophy("Visit an external website");

        var websiteCounter = 0;
        $cordovaSQLite.execute(db, "SELECT counter FROM progress WHERE objective LIKE 'Visit 5 websites'", []).then(function (result) {
            if (result.rows.length > 0) {
                websiteCounter = result.rows.item(0).counter + 1;
                console.log("Website Counter = " + websiteCounter);

                if (websiteCounter == 5) {
                    dbService.updateTrophy("Visit 5 external websites");
                }
                $cordovaSQLite.execute(db, "UPDATE progress SET counter = " + websiteCounter + " WHERE objective LIKE 'Visit 5 websites'", []);

            } else {
                console.error("ACCESSING PROGRESS FAILED AT FUNCTION - loadingWebsite");
            }
        }, function (error) {
            console.error(error);
        });
    };

    /**
     * This is to update calling a phone number
     */
    this.callingPhone = function () {
        dbService.updateTrophy("Call a phone number");
    };

    /**
     * This is to update the quizzes
     */
    this.updateQuiz = function (percentageInQuiz) {
        dbService.updateTrophy("Attempt a quiz");

        var quizCounter = 0;
        $cordovaSQLite.execute(db, "SELECT counter FROM progress WHERE objective LIKE 'Quiz Counter'", []).then(function (result) {
            if (result.rows.length > 0) {
                quizCounter = result.rows.item(0).counter + 1;
                console.log("Quiz Counter = " + quizCounter);

                var returnQuizCountPromise = dbService.returnQuizCount();
                returnQuizCountPromise.then(function (quizCount) {
                    if (quizCounter == 5) {
                        dbService.updateTrophy("Attempted 5 quizzes");
                        // Unlock trophy when user attempts 5 quizzes
                    } else if (quizCounter == quizCount) {
                        dbService.updateTrophy("Attempted all quizzes");
                        // Unlock trophy when user attempts all quizzes
                    }
                });

                if (percentageInQuiz == 100) {
                    dbService.updateTrophy("Get 100% in a quiz");
                    var percentageInQuizCounter = 0;
                    $cordovaSQLite.execute(db, "SELECT counter FROM progress WHERE objective LIKE '100% Quiz Counter'", []).then(function (result) {
                        if (result.rows.length > 0) {
                            percentageInQuizCounter = result.rows.item(0).counter + 1;
                            console.log("100% Quiz Counter = " + percentageInQuizCounter);

                            if (percentageInQuizCounter == 3) {
                                dbService.updateTrophy("Get 100% in 3 quizzes");
                            }

                            $cordovaSQLite.execute(db, "UPDATE progress SET counter = " + percentageInQuizCounter + " WHERE objective LIKE '100% Quiz Counter'", []);

                        } else {
                            console.error("ACCESSING PROGRESS FAILED AT FUNCTION - updateQuiz");
                        }
                    }, function (error) {
                        console.error(error);
                    });
                } else {
                    $cordovaSQLite.execute(db, "UPDATE progress SET counter = 0 WHERE objective LIKE '100% Quiz Counter'", []);
                }

                $cordovaSQLite.execute(db, "UPDATE progress SET counter = " + quizCounter + " WHERE objective LIKE 'Quiz Counter'", []);

            } else {
                console.error("ACCESSING PROGRESS FAILED AT FUNCTION - updateQuiz");
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
            case 4:
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
            if (result.rows.length == 4) {
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
                console.error("ACCESSING PROGRESS FAILED AT incrementTipCount FUNCTION");
            }
        }, function (error) {
            console.error(error);
        });
    };

    /**
     * This is to update the categories progress
     */
    this.updateCategoryProgress = function () {
        var q = $q.defer();
        var executeCounter = 0;
        var categoryQuery = "SELECT id FROM categories";
        $cordovaSQLite.execute(db, categoryQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                for (var i = 0; i < result.rows.length; i++) {
                    var subCategoryQuery = "SELECT percentageComplete, categoryID FROM subcategories WHERE categoryID = " + result.rows.item(i).id;
                    $cordovaSQLite.execute(db, subCategoryQuery, []).then(function (subresult) {
                        if (subresult.rows.length > 0) {
                            var percentageCounter = 0;

                            for (var j = 0; j < subresult.rows.length; j++) {
                                percentageCounter += subresult.rows.item(j).percentageComplete;
                            }

                            percentageCounter = percentageCounter / subresult.rows.length;

                            $cordovaSQLite.execute(db, "UPDATE categories SET percentageComplete = " + percentageCounter + " WHERE id = " + subresult.rows.item(0).categoryID, []).then(function (counter) {
                                executeCounter++;
                                if (executeCounter == (result.rows.length - 1)) {
                                    console.log("Resolve Here!");
                                    q.resolve(executeCounter);
                                }
                            }, function (error) {
                                console.error(error);
                            });
                            console.log("UPDATE categories SET percentageComplete = " + percentageCounter + " WHERE id = " + subresult.rows.item(0).categoryID);
                        }
                    }, function (error) {
                        console.error(error);
                    });
                }
            } else {
                console.error("Error In categories table");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    };

    /**
     * This gets the name of the subcategory from the quiz you are in
     */
    this.getSubCatName = function (quizURL) {
        var q = $q.defer();
        var subCatName;
        var query = "SELECT name FROM subcategories WHERE quizUrL = '" + quizURL + "'";
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            console.log("This is the result: " + JSON.stringify(result));
            if (result.rows.length > 0) {
                subCatName = result.rows.item(0).name;
                q.resolve(subCatName);
            } else {
                console.error("getSubCatID function failed");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    }

    /**
     * This is to update the subcategories progress
     */
    this.updateSubCategoryProgress = function (subCatName, quizScore) {
        var q = $q.defer();
        var name = subCatName;
        var progress;
        var newScore = quizScore;
        var query = "SELECT * FROM subcategories WHERE name LIKE '" + name + "'";
        $cordovaSQLite.execute(db, query, []).then(function (results) {
            if (results.rows.length == 1) {
                progress = results.rows.item(0).percentageComplete;
                if (newScore > progress) {
                    var updateQuery = "UPDATE subcategories SET percentageComplete = " + newScore + " WHERE name LIKE '" + name + "'";
                    $cordovaSQLite.execute(db, updateQuery, []).then(function (results) {
                        console.log("SCORE HAS BEEN UPDATED TO: " + newScore);
                        q.resolve(progress);
                    });
                }
            } else {
                console.error("updateSubCategoryProgress FUNCTION FAILED");
            }
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    }

    /**
     * This is to find out the overall progress of the application
     */
    this.checkOverallProgress = function () {
        var progressCount = 0;
        $cordovaSQLite.execute(db, "SELECT percentageComplete FROM categories", []).then(function (result) {
            if (result.rows.length > 0) {
                for (var i = 0; i < result.rows.length; i++) {
                    progressCount += result.rows.item(i).percentageComplete;
                }
                progressCount = Math.floor(progressCount / result.rows.length);

                console.log("Overall Progress = " + progressCount);

                if (progressCount >= 25 && progressCount < 50) {
                    dbService.updateTrophy("Achieve 25% completion");
                    // Unlock trophy when user reaches 25% completion
                } else if (progressCount >= 50 && progressCount < 75) {
                    dbService.updateTrophy("Achieve 25% completion");
                    dbService.updateTrophy("Achieve 50% completion");
                    // Unlock trophy when user reaches 50% completion
                } else if (progressCount >= 75 && progressCount < 100) {
                    dbService.updateTrophy("Achieve 25% completion");
                    dbService.updateTrophy("Achieve 50% completion");
                    dbService.updateTrophy("Achieve 75% completion");
                    // Unlock trophy when user reaches 75% completion
                } else if (progressCount == 100) {
                    dbService.updateTrophy("Achieve 25% completion");
                    dbService.updateTrophy("Achieve 50% completion");
                    dbService.updateTrophy("Achieve 75% completion");
                    dbService.updateTrophy("Achieve 100% completion");
                    // Unlock trophy when user reaches 100% completion
                }

            } else {
                console.error("ACCESSING PROGRESS FAILED AT checkOverallProgress FUNCTION");
            }
        }, function (error) {
            console.error(error);
        });
    }

    this.dropAllTables = function () {
        $cordovaSQLite.execute(db, "DROP TABLE userData");
        $cordovaSQLite.execute(db, "DROP TABLE trophies");
        $cordovaSQLite.execute(db, "DROP TABLE categories");
        $cordovaSQLite.execute(db, "DROP TABLE subcategories");
        $cordovaSQLite.execute(db, "DROP TABLE progress");
    }

    this.buildTables = function () {
        $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS userData (id INTEGER PRIMARY KEY, name TEXT, location TEXT, avatar TEXT)");
        $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS trophies (id INTEGER PRIMARY KEY, title TEXT, image TEXT, description TEXT, hint TEXT, acquired TINYINT)");
        $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS categories (id INTEGER, name NVARCHAR(50), percentageComplete INTEGER)");
        $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS subcategories (id INTEGER PRIMARY KEY, name NVARCHAR(50), quizURL NVARCHAR(50), percentageComplete INTEGER, categoryID INTEGER, FOREIGN KEY(categoryID) REFERENCES categories(id))");
        $cordovaSQLite.execute(db, "CREATE TABLE IF NOT EXISTS progress (objective NVARCHAR(50) PRIMARY KEY, counter INTEGER, valueChanged TINYINT)");
    }

    this.setGlobalName = function () {
        // Check if data has been added correctly
        var searchQuery = "SELECT * FROM userData";
        var userName = { name: "", location: "", avatar: "img/startImage.png" }
        $cordovaSQLite.execute(db, searchQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                userName.name = result.rows.item(0).name;
                userName.location = result.rows.item(0).location;
                userName.avatar = result.rows.item(0).avatar;
                $rootScope.userName = userName;
            } else {
                console.error("NO ROWS EXIST IN userData");
            }
        }, function (error) {
            console.error(error);
        });
    }

    this.fillTables = function () {
        /**
        * User Data Table
        */
        var query = "INSERT INTO userData (name, location, avatar) VALUES (?,?,?)";
        $cordovaSQLite.execute(db, query, ["Your Name Here", "Your Location Here", "img/startImage.png"]).then(function (result) {
            console.log("INSERT USER ID -> " + result.insertId);
        }, function (error) {
            console.error(error);
        });

        /**
         * Trophies Table
         */
        var query = "INSERT INTO trophies (title, image, description, hint, acquired) VALUES (?,?,?,?,?)";
        $cordovaSQLite.execute(db, query, ["Updated Your Name", "img/trophy1.png", "You have successfully updated your name.", "Try updating your name.", 0]);
        $cordovaSQLite.execute(db, query, ["Updated Your Location", "img/trophy2.png", "You have successfully updated your location.", "Try updating your location.", 0]);
        $cordovaSQLite.execute(db, query, ["Updated Your Picture", "img/trophy3.png", "You have successfully updated your picture.", "Try updating your picture.", 0]);
        $cordovaSQLite.execute(db, query, ["Flicked through 20 hints", "img/trophy4.png", "You have managed to flick through 20 hints.", "Try flicking through more hints.", 0]);
        $cordovaSQLite.execute(db, query, ["Flicked through 50 hints", "img/trophy5.png", "You have managed to flick through 50 hints.", "Try flicking through more hints.", 0]);
        $cordovaSQLite.execute(db, query, ["Flicked through 100 hints", "img/trophy6.png", "You have managed to flick through 100 hints.", "Try flicking through more hints.", 0]);
        $cordovaSQLite.execute(db, query, ["Performed a calculation on a calculator", "img/trophy7.png", "You performed a calculation on a calculator.", "Try using a calculator.", 0]);
        $cordovaSQLite.execute(db, query, ["Performed a calculation on all the calculators", "img/trophy8.png", "You performed a calculation on all the calculators.", "Try using all calculators.", 0]);
        $cordovaSQLite.execute(db, query, ["Visit an external website", "img/trophy9.png", "You visited an external website.", "Try visiting a website.", 0]);
        $cordovaSQLite.execute(db, query, ["Visit 5 external websites", "img/trophy10.png", "You visited 5 external websites.", "Try visiting more websites.", 0]);
        $cordovaSQLite.execute(db, query, ["Call a phone number", "img/trophy11.png", "You called a phone number.", "Try calling someone.", 0]);
        $cordovaSQLite.execute(db, query, ["Achieve 25% completion", "img/trophy12.png", "You have completed 25% of the application.", "Try completing more of the application.", 0]);
        $cordovaSQLite.execute(db, query, ["Achieve 50% completion", "img/trophy13.png", "You have completed 50% of the application.", "Try completing more of the application.", 0]);
        $cordovaSQLite.execute(db, query, ["Achieve 75% completion", "img/trophy14.png", "You have completed 75% of the application.", "Try completing more of the application.", 0]);
        $cordovaSQLite.execute(db, query, ["Achieve 100% completion", "img/trophy15.png", "You have completed 100% of the application.", "Try completing all of the application.", 0]);
        $cordovaSQLite.execute(db, query, ["Attempt a quiz", "img/trophy16.png", "You have attempted a quiz.", "Try attempting a quiz.", 0]);
        $cordovaSQLite.execute(db, query, ["Attempted 5 quizzes", "img/trophy17.png", "You have attempted 5 quizzes.", "Try attempting more quizzes.", 0]);
        $cordovaSQLite.execute(db, query, ["Attempted all quizzes", "img/trophy18.png", "You have attempted all quizzes.", "Try attempting all the quizzes.", 0]);
        $cordovaSQLite.execute(db, query, ["Get 100% in a quiz", "img/trophy19.png", "You have achieved 100% in a quiz.", "Try getting full marks in a quiz.", 0]);
        $cordovaSQLite.execute(db, query, ["Get 100% in 3 quizzes", "img/trophy20.png", "You have achieved 100% in 3 quizzes.", "Try getting full marks in multiple quizzes.", 0]);
        $cordovaSQLite.execute(db, query, ["Unlock all the trophies", "img/trophy21.png", "You have unlocked every trophy, Congratulations!", "Try getting more trophies.", 0]);

        /**
         * Categorys Table
         */
        var catQuery = "INSERT INTO categories (id, name, percentageComplete) VALUES (?,?,?)";
        $http.get('content/categories.json')
            .then(function (categories) {
                for (var i = 0; i < categories.data.length; i++) {
                    $cordovaSQLite.execute(db, catQuery, [categories.data[i].ID, categories.data[i].name, 0]).then(function (result) {
                        console.log("INSERT CAT ID -> " + result.insertId);
                    }, function (error) {
                        console.error("ERROR INSERTING CAT:" + JSON.stringify(error));
                    });
                }
            });

        /**
         * Subcategories Table
         */
        var subCatQuery = "INSERT INTO subcategories (name, quizURL, percentageComplete, categoryID) VALUES (?,?,?,?)";
        $http.get('content/topics.json')
            .then(function (subcategories) {
                for (var i = 0; i < subcategories.data.length; i++) {
                    var SCname;
                    var SCcatID;
                    var SCquiz;

                    $http.get("content/topics/" + subcategories.data[i])
                        .then(function (subcategory) {
                            var ref = subcategory.data.reference;
                            $http.get('content/categories.json')
                                .then(function (category) {
                                    for (var j = 0; j < category.data.length; j++) {
                                        if (category.data[j].ID === ref) {
                                            var getNameQuery = "SELECT id FROM categories WHERE name LIKE '" + category.data[j].name + "'";

                                            $cordovaSQLite.execute(db, getNameQuery, []).then(function (result) {
                                                SCcatID = result.rows.item(0).id;
                                                SCname = subcategory.data.title;
                                                SCquiz = subcategory.data.quiz.url;

                                                $cordovaSQLite.execute(db, subCatQuery, [SCname, SCquiz, 0, SCcatID]).then(function (result) {
                                                    console.log("INSERT SUB CAT ID -> " + result.insertId);
                                                }, function (error) {
                                                    console.error("ERROR INSERTING SUB CAT:" + JSON.stringify(error));
                                                });
                                            }, function (error) {
                                                console.error(error);
                                            });
                                        }
                                    }
                                });
                        });
                }
            });

        /**
         * Progress Table
         */
        var progressQuery = "INSERT INTO progress (objective, counter, valueChanged) VALUES (?,?,?)";
        $cordovaSQLite.execute(db, progressQuery, ["Tip Counter", 0, null]);
        $cordovaSQLite.execute(db, progressQuery, ["Perform calc 1", null, "false"]);
        $cordovaSQLite.execute(db, progressQuery, ["Perform calc 2", null, "false"]);
        $cordovaSQLite.execute(db, progressQuery, ["Perform calc 3", null, "false"]);
        $cordovaSQLite.execute(db, progressQuery, ["Perform calc 4", null, "false"]);
        $cordovaSQLite.execute(db, progressQuery, ["Visit 5 websites", 0, null]);
        $cordovaSQLite.execute(db, progressQuery, ["Quiz Counter", 0, null]);
        $cordovaSQLite.execute(db, progressQuery, ["100% Quiz Counter", 0, null]);
    }

    // Returns all the categories from JSON file.
    // This is used for Content Management System integration.
    this.getCatCount = function () {
        var q = $q.defer();
        var cat = [];
        $http.get('content/categories.json')
            .then(function (categories) {
                for (var i = 0; i < categories.data.length; i++) {
                    cat[i] = categories.data[i].name;
                }
                q.resolve(cat);
            });
        return q.promise;
    }

    // Returns all the categories from the database.
    // This is used for Content Management System integration.
    this.getDataCatCount = function () {
        var q = $q.defer();
        var cat = [];
        var query = "SELECT * FROM categories";
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            count = result.rows.length;
            for (var i = 0; i < result.rows.length; i++) {
                cat[i] = result.rows.item(i).name;
            }
            q.resolve(cat);
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    }

    // Returns all the subcategories from the database.
    // This is used for Content Management System integration.
    this.getSubCatCount = function () {
        var q = $q.defer();
        var subCat = [];
        $http.get('content/topics.json')
            .then(function (subcategories) {
                for (var i = 0; i < subcategories.data.length; i++) {
                    function getCount(i) {
                        var SCname;
                        $http.get("content/topics/" + subcategories.data[i])
                            .then(function (subcategory) {
                                var ref = subcategory.data.reference;
                                $http.get('content/categories.json')
                                    .then(function (category) {
                                        for (var j = 0; j < category.data.length; j++) {
                                            if (category.data[j].ID === ref) {
                                                var getNameQuery = "SELECT id FROM categories WHERE name LIKE '" + category.data[j].name + "'";

                                                $cordovaSQLite.execute(db, getNameQuery, []).then(function (result) {
                                                    SCname = subcategory.data.title;
                                                    subCat[i] = SCname
                                                    q.resolve(subCat);
                                                }, function (error) {
                                                    console.error(error);
                                                });
                                            }
                                        }

                                    });

                            });

                    } (getCount(i));

                }

            });

        return q.promise;
    }

    // Returns all the subcategories from the database.
    // This is used for Content Management System integration.
    this.getDataSubCatCount = function () {
        var q = $q.defer();
        var subcat = [];
        var query = "SELECT * FROM subcategories";
        $cordovaSQLite.execute(db, query, []).then(function (result) {
            count = result.rows.length;
            for (var i = 0; i < result.rows.length; i++) {
                subcat[i] = result.rows.item(i).name;
            }
            q.resolve(subcat);
        }, function (error) {
            console.error(error);
        });
        return q.promise;
    }

    this.updateTablesFromCMS = function () {

        // UPDATE CATEGORIES TABLE

        var catCount = dbService.getCatCount();
        catCount.then(function (catOutput) {
            var dataCatCount = dbService.getDataCatCount();
            dataCatCount.then(function (dataOutput) {
                console.log("NUMBER OF JSON CATEGORIES: " + catOutput.length);
                console.log("NUMBER OF DATABASE CATEGORIES: " + dataOutput.length);
                var duplicateAddArray = catOutput.filter(function (val) {
                    return dataOutput.indexOf(val) == -1;
                })
                var duplicateDeleteArray = dataOutput.filter(function (val) {
                    return catOutput.indexOf(val) == -1;
                })
                console.log("NUMBER OF ADDED CATEGORIES: " + duplicateAddArray.length);
                console.log("NUMBER OF DELETED CATEGORIES: " + duplicateDeleteArray.length);
                if (duplicateAddArray.length > 0) {
                    // SOMETHING HAS BEEN ADDED BY THE CONTENT MANAGEMENT SYSTEM
                    for (var i = 0; i < duplicateAddArray.length; i++) {
                        var check = false;
                        for (var j = 0; j < dataOutput.length; j++) {
                            if (duplicateAddArray[i] == dataOutput[j]) {
                                // This has been removed by content management system
                                check = true;
                            }
                        }
                        if (check) {
                            // THIS WILL NEVER BE THE CASE
                        } else {
                            // Add category from here
                            console.log("CATEGORY WAS ADDED: " + JSON.stringify(duplicateAddArray[i]));
                            var addQuery = "INSERT INTO categories (id, name, percentageComplete) VALUES (?,?,?)";
                            //console.log(JSON.stringify(duplicateAddArray[i]))
                            function passCatName(catName) {
                                var catIDPromise = dbService.returnIDFromName(duplicateAddArray[i]);
                                catIDPromise.then(function (catID) {
                                    $cordovaSQLite.execute(db, addQuery, [catID, catName, 0]).then(function (result) {
                                        console.log("ADDED CATEGORY -> " + result.insertId);
                                    }, function (error) {
                                        console.error("ERROR INSERTING CAT BY CMS:" + JSON.stringify(error));
                                    });
                                });
                            } (passCatName(duplicateAddArray[i]))



                            // PERCENTAGE COMPLETE IS NOT BEING ADDED CORRECTLY 
                        }
                    }
                }
                if (duplicateDeleteArray.length > 0) {
                    // SOMETHING HAS BEEN DELETED BY THE CONTENT MANAGEMENT SYSTEM
                    for (var i = 0; i < duplicateDeleteArray.length; i++) {
                        var check = false;
                        for (var j = 0; j < dataOutput.length; j++) {
                            if (duplicateDeleteArray[i] == dataOutput[j]) {
                                // This has been removed by content management system
                                check = true;
                            }
                        }
                        if (check) {
                            // Remove category from here
                            //console.log("CATEGORY WAS DELETED: " + JSON.stringify(duplicateDeleteArray[i]));
                            var deleteQuery = "DELETE FROM categories WHERE name LIKE '" + duplicateDeleteArray[i] + "'";;
                            $cordovaSQLite.execute(db, deleteQuery, []).then(function (result) {
                                console.log("DELETED CATEGORY -> " + result.insertId);
                            }, function (error) {
                                console.error("ERROR DELETEING CAT BY CMS:" + JSON.stringify(error));
                            });
                        } else {
                            // THIS WILL NEVER BE THE CASE
                        }
                    }
                }
            });
        });

        // UPDATE SUBCATEGORIES TABLE

        var subCatCount = dbService.getSubCatCount();
        subCatCount.then(function (subCatOutput) {
            var dataSubCatCount = dbService.getDataSubCatCount();
            dataSubCatCount.then(function (dataSubOutput) {
                console.log("NUMBER OF JSON SUBCATEGORIES: " + subCatOutput.length); // WRONG LENGTH FOUND
                console.log("NUMBER OF DATABASE SUBCATEGORIES: " + dataSubOutput.length);
                var duplicateSubCatAddArray = subCatOutput.filter(function (val) {
                    return dataSubOutput.indexOf(val) == -1;
                })
                var duplicateSubCatDeleteArray = dataSubOutput.filter(function (val) {
                    return subCatOutput.indexOf(val) == -1;
                })
                console.log("NUMBER OF ADDED SUBCATEGORIES: " + duplicateSubCatAddArray.length);
                console.log("NUMBER OF DELETED SUBCATEGORIES: " + duplicateSubCatDeleteArray.length);
                if (duplicateSubCatAddArray.length > 0) {
                    // SOMETHING HAS BEEN ADDED BY THE CONTENT MANAGEMENT SYSTEM
                    for (var i = 0; i < duplicateSubCatAddArray.length; i++) {
                        var check = false;
                        for (var j = 0; j < dataSubOutput.length; j++) {
                            if (duplicateSubCatAddArray[i] == dataSubOutput[j]) {
                                // This has been removed by content management system
                                check = true;
                            }
                        }
                        if (check) {
                            // THIS IS NEVER THE CASE
                        } else {
                            // Add subcategory from here
                            var newSubCat = duplicateSubCatAddArray[i];
                            console.log("SUBCATEGORY WAS ADDED: " + newSubCat);

                            function passSubCat(newSubCat) {
                                $http.get('content/topics.json')
                                    .then(function (subcategories) {
                                        for (var j = 0; j < subcategories.data.length; j++) {
                                            $http.get("content/topics/" + subcategories.data[j])
                                                .then(function (subcategory) {
                                                    if (subcategory.data.title === newSubCat) {
                                                        var SCname = subcategory.data.title;
                                                        var SCcatID = subcategory.data.reference;
                                                        var SCquiz = subcategory.data.quiz.url;
                                                        var addQuery = "INSERT INTO subcategories (name, quizURL, percentageComplete, categoryID) VALUES (?,?,?,?)";
                                                        $cordovaSQLite.execute(db, addQuery, [SCname, SCquiz, 0, SCcatID]).then(function (result) {
                                                            console.log("ADDED SUBCATEGORY -> " + result.insertId);
                                                            dbService.updateCategoryProgress();
                                                        }, function (error) {
                                                            console.error("ERROR INSERTING SUB CAT BY CMS:" + JSON.stringify(error));
                                                        });
                                                    }
                                                });
                                        }
                                    });
                            } (passSubCat(newSubCat));
                        }
                    }
                }
                if (duplicateSubCatDeleteArray.length > 0) {
                    // SOMETHING HAS BEEN DELETED BY THE CONTENT MANAGEMENT SYSTEM
                    for (var i = 0; i < duplicateSubCatDeleteArray.length; i++) {
                        var check = false;
                        for (var j = 0; j < dataSubOutput.length; j++) {
                            if (duplicateSubCatDeleteArray[i] == dataSubOutput[j]) {
                                // This has been removed by content management system
                                check = true;
                            }
                        }
                        if (check) {
                            // Remove category from here
                            console.log("CATEGORY WAS DELETED: " + JSON.stringify(duplicateSubCatDeleteArray[i]));
                            var deleteQuery = "DELETE FROM subcategories WHERE name LIKE '" + duplicateSubCatDeleteArray[i] + "'";;
                            $cordovaSQLite.execute(db, deleteQuery, []).then(function (result) {
                                console.log("DELETED SUBCATEGORY -> " + result.insertId);
                                dbService.updateCategoryProgress();
                            }, function (error) {
                                console.error("ERROR DELETING SUB CAT BY CMS:" + JSON.stringify(error));
                            });
                        } else {
                            // THIS WILL NEVER BE THE CASE
                        }
                    }
                }
            });
        });
    };

    this.printCategoriesTable = function () {
        var printQuery = "SELECT * FROM categories ORDER BY id";
        $cordovaSQLite.execute(db, printQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                console.log("");
                console.log("Number of Categories = " + result.rows.length);
                for (var i = 0; i < result.rows.length; i++) {
                    console.log("");
                    console.log("Category ID = " + result.rows.item(i).id + " - name = " + result.rows.item(i).name + " - percentageComplete = " + result.rows.item(i).percentageComplete);
                }
            }
        }, function (error) {
            console.error(JSON.stringify(error));
        });
    };

    this.printSubCategoriesTable = function () {
        var printQuery = "SELECT * FROM subcategories ORDER BY id";
        $cordovaSQLite.execute(db, printQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                console.log("");
                console.log("Number of Subcategories = " + result.rows.length);
                for (var i = 0; i < result.rows.length; i++) {
                    console.log("");
                    console.log("Sub Category ID = " + result.rows.item(i).id + " - name = " + result.rows.item(i).name + " - percentageComplete = " + result.rows.item(i).percentageComplete + " - quizURL = " + result.rows.item(i).quizURL + " - CategoryID = " + result.rows.item(i).categoryID);
                }
            }
        }, function (error) {
            console.error(JSON.stringify(error));
        });
    };

    this.returnIDFromName = function (name) {
        var q = $q.defer();
        $http.get('content/categories.json')
            .then(function (categories) {
                for (var i = 0; i < categories.data.length; i++) {
                    if (categories.data[i].name == name) {
                        q.resolve(categories.data[i].ID);
                    }
                }
            });
        return q.promise;
    };

    this.returnQuizCount = function () {
        var q = $q.defer();
        var printQuery = "SELECT * FROM subcategories";
        $cordovaSQLite.execute(db, printQuery, []).then(function (result) {
            if (result.rows.length > 0) {
                console.log("Number of Subcategories = " + result.rows.length);
                q.resolve(result.rows.length);
            }
        }, function (error) {
            console.error(JSON.stringify(error));
        });
        return q.promise;
    };

});
