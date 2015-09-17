
<div class="starter-template">
<h1>Registrate para comenzar !!</h1>

<div class="panel panel-default">
  <div class="panel-body">
    <form class="form-horizontal" role="form" id='article-create-form' method='POST' <#if clientes??> action="/user/create" </#if> >
        
        <div class="form-group">
            <label class="col-sm-3 control-label" for="title">Nombre: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="title" name='article-title' placeholder="Nombre" maxlength="30" <#if clientes??> value="${clientes.getTitle()}" </#if> />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="summary">Usuario: </label>
            <div class="col-sm-5">
                <input class="form-control" type='text' id="summary" name='article-summary' placeholder="Nombre de usuario" maxlength="30" <#if clientes??> value="${clientes.getSummary()}" </#if>  />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label" for="content">Clave: </label>
            <div class="col-sm-5">
                <input class="form-control" name='article-content' id="content"  form='article-create-form' placeholder="Clave de acceso" maxlength="30" <#if clientes??> value="${clientes.getContent()}" </#if> />
            </div>
        </div>

        <#if article??>
            <input type='hidden' name='article-id' <#if clientes??> value="${clientes.getId()}" </#if> />
        </#if>
  </div>
    </form>

  <div class="panel-footer">
     <input type='submit' <#if clientes??> value='Enviar' </#if> class="btn btn-primary" form='article-create-form' />

</div>
  </div>
  
</div>

</div>