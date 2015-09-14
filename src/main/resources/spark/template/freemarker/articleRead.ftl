<div class="starter-template">



<div class="page-header">
  <h1>${article.getTitle()}</h1>
</div>
<h1><small>${article.getCreatedAt()}</small></h1>
<div class="panel panel-primary">
<div class="panel-body">${article.getContent()}</div>
</div>
<h4>${article.getEditLink()} | ${article.getDeleteLink()}</h4>

<div>
