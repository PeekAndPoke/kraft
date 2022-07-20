```ascii-art
                                                             
        _/    _/  _/_/_/      _/_/    _/_/_/_/  _/_/_/_/_/   
       _/  _/    _/    _/  _/    _/  _/            _/        
      _/_/      _/_/_/    _/_/_/_/  _/_/_/        _/         
     _/  _/    _/    _/  _/    _/  _/            _/          
    _/    _/  _/    _/  _/    _/  _/            _/           
                                                             
```

# Check out the examples

## Hello-World example

[Try it out](https://rawgit.com/PeekAndPoke/kraft/master/docs/examples/hello-world/index.html) |
[See the code](./examples/hello-world)

Run it locally
> ./gradlew -t --parallel :examples:hello-world:run

## Addon examples

[Try it out](https://rawgit.com/PeekAndPoke/kraft/master/docs/examples/addons/index.html) |
[See the code](./examples/addons)

Run it locally
> ./gradlew -t --parallel :examples:addons:run

## SemanticUi / FomanticUi examples

[Try it out](https://rawgit.com/PeekAndPoke/kraft/master/docs/examples/semanticui/index.html)
[See the code](./examples/semanticui)

Run it locally
> ./gradlew -t --parallel :examples:semanticui:run

# Run the examples locally

Run the Hello World example

> ./gradlew -t --parallel --configure-on-demand :examples:hello-world:run

Run the SemanticUI / FomanticUI examples

> ./gradlew -t --parallel --configure-on-demand :examples:semanticui:run
