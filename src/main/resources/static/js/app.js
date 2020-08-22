var app = (function(){

    var draw = (data)=>{
        $("#peoples").html("");
        data.forEach((people)=>{
            $("#peoples").append($("<div><div>Name:"+people.name+"</div><div>  -Age:"+people.age+"</div> <div>-Country:"+people.country+"</div></div>"));
            console.log(people);
            });
        console.log(data);
    }

    return{

    loadData:function(){
        client.loadData(draw);
    },
    add:function(){
        var name=$("#name").val();
        var age = $("#age").val();
        var country = $("#country").val();
        client.add(name,age,country,app.loadData);
        console.log(name+" "+age+" "+country);

    }
    };
})();