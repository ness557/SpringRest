 

//create and choose database
use intendship;

//save it
db.users.save({username:"intendship"});

//drop seq if exists
db.sequence.drop();

//insert some values into some documents
//role document
//drop if exists
db.role.drop();

// insert values
db.role.insert({ "_id" : 1, "_class" : "ness.model.Role", "name" : "ADMIN"});
db.role.insert({ "_id" : 2, "_class" : "ness.model.Role", "name" : "USER"});

//create sequences for auto increment id
db.sequence.insert({_id: "ROLE_SEQ", number: 2});

//user document
//drop doc if exists
db.user.drop()

//insert values
db.user.insert({ "_id" : 1, "_class" : "ness.model.User", "firstName" : "Roman", "lastName" : "Bezruchko", 
	"userInfo" : 
	{ 
		"email" : "ness55779@gmail.com", 
		"phone" : 0931691478 
	}, 
	"roles" : 
	[ 
	{ "_id" : 1, "_class" : "ness.model.Role", "name" : "ADMIN" },  
	{ "_id" : 2, "_class" : "ness.model.Role", "name" : "USER"}
	] 
})
db.user.insert({ "_id" : 2, "_class" : "ness.model.User", "firstName" : "Ivan", "lastName" : "Ivanov", 
	"userInfo" : 
	{ 
		"email" : "ivanIvanovgmail.com", 
		"phone" : 0669031120 
	}, 
	"roles" : 
	[ 
	{ "_id" : 1, "_class" : "ness.model.Role", "name" : "ADMIN" },  
	{ "_id" : 2, "_class" : "ness.model.Role", "name" : "USER"}
	] 
})

//create sequences for auto increment id
db.sequence.insert({_id: "USER_SEQ", number: 2});