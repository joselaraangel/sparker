
<div class="starter-template">
<div class="panel panel-default">
  <div class="panel-body">
    <form class="form-horizontal" role="form" id='article-create-form' method='POST' <#if article??>action="/article/update/:id"<#else>action="/article/create"</#if>>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="title">Titulo: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="title" name='article-title' placeholder="Escribe un titulo" maxlength="120"<#if article??>value="${article.getTitle()}"</#if> />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="summary">Resumen: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="summary" name='article-summary' placeholder="Escribe un Resumen" maxlength="220"<#if article??>value="${article.getSummary()}"</#if> />
            </div>
        </div>
        <#if article??>
            <input type='hidden' name='article-id' value="${article.getId()}" />
        </#if>
  </div>
    </form>

  <div class="panel-footer">
<label for="content">Contenido</label>
     <textarea class="form-control" name='article-content' id="content" rows='4' cols='50' form='article-create-form' placeholder="Escribe el contenido" maxlength="620"><#if article??>${article.getContent()}</#if></textarea>
     <input type='submit' <#if article??>value='Actualizar'<#else>value='Publicar'</#if> class="btn btn-primary" form='article-create-form' />

</div>
  </div>
  
</div>

</div>