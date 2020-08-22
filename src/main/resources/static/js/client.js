var client = (function(){
    return{
        add:function(name,age,country,fun){
            $.get({url:"/addPeople?name="+name+"&&age="+age+"&&country="+country}).then(()=>fun(),()=>alert("error"));
        },
        loadData:function(fun){
            $.get({url:"/peoples"}).then((data)=>fun(JSON.parse(data)),(er)=>alert("error"));
        }
    };
})();