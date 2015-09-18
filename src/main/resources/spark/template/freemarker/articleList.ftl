<#if hasNoArticles??>
    <div class="starter-template">
        <h1>${hasNoArticles}</h1>
    </div>
<#else>
    <div class="starter-template">
        <#list articles as article>



<table class="table table-bordered table table-hover">
              <tr  class="warning">
<td width="35%"><strong>Titulo</strong></td>
<td width="10%"><strong>Fecha</strong></td>
<td width="35%"><strong>Descripcion</strong></td>
<td width="10%"><strong>Editar</strong></td>
<td width="10%"><strong>Eliminar</strong></td>
</tr>

<tr>
<td>${article.getTitle()}</td>
<td>${article.getCreatedAt()}</td>
<td>${article.getSummaryLink()}</td>
<td>${article.getEditLink()}</td>
<td>${article.getDeleteLink()}</td>
</tr>


</table>
 </#list>

    </div>
</#if>