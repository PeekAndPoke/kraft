(globalThis.webpackChunkfomanticui=globalThis.webpackChunkfomanticui||[]).push([[488],{488:()=>{!function(e){function n(e,n){return"___"+e.toUpperCase()+n+"___"}Object.defineProperties(e.languages["markup-templating"]={},{buildPlaceholders:{value:function(t,a,o,r){if(t.language===a){var i=t.tokenStack=[];t.code=t.code.replace(o,(function(e){if("function"==typeof r&&!r(e))return e;for(var o,c=i.length;-1!==t.code.indexOf(o=n(a,c));)++c;return i[c]=e,o})),t.grammar=e.languages.markup}}},tokenizePlaceholders:{value:function(t,a){if(t.language===a&&t.tokenStack){t.grammar=e.languages[a];var o=0,r=Object.keys(t.tokenStack);!function i(c){for(var u=0;u<c.length&&!(o>=r.length);u++){var g=c[u];if("string"==typeof g||g.content&&"string"==typeof g.content){var l=r[o],s=t.tokenStack[l],p="string"==typeof g?g:g.content,f=n(a,l),k=p.indexOf(f);if(k>-1){++o;var h=p.substring(0,k),m=new e.Token(a,e.tokenize(s,t.grammar),"language-"+a,s),b=p.substring(k+f.length),d=[];h&&d.push.apply(d,i([h])),d.push(m),b&&d.push.apply(d,i([b])),"string"==typeof g?c.splice.apply(c,[u,1].concat(d)):g.content=d}}else g.content&&i(g.content)}return c}(t.tokens)}}}})}(Prism)}}]);
//# sourceMappingURL=488.js.map