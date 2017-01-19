fIApp.service("dbAccessor", function($cordovaSQLite){
              var name = "";
              
              this.setName = function(fullname){
              name = fullname;
              }
              
              this.getName = function(){
              return name;
              }
              
              this.testPrint = function(){
              console.log("Testing print method");
              }
              
              this.testReturn = function(){
              return "Testing the return method";
              }
              
              this.insertName = function(firstname, lastname){
              console.log("in the insert function");
              var query = "INSERT INTO people (firstname, lastname) VALUES (?,?)";
              console.log("made query");
              $cordovaSQLite.execute(db,query,[firstname,lastname]).then(function(result) {
                console.log("INSERT ID -> " + result.insertId);
                }, function(error) {
                    console.error(error);
                });
              
              };
              
              this.selectName = function(lastname) {
              var query = "SELECT firstname, lastname FROM people WHERE lastname = ?";
              $cordovaSQLite.execute(db,query,[lastname]).then(function(result) {
                    if(result.rows.length > 0) {
                    console.log("SELECTED -> " + result.rows.item(0).firstname + " " + result.rows.item(0).lastname);
                        } else {
                            console.log("NO ROWS EXIST");
                            }
                        }, function(error) {
                    console.error(error);
                        });
              };
              
              
              
              })
