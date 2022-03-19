<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if post?? && user??>
        <@c.post post=post/>


        <div class="form">
            <div class="header">Write Comment</div>
            <div class="body">
                <form method="post" action="">
                    <input type="hidden" name="action" value="writeComment"/>
                    <div class="field">
                        <div class="name">
                            <label for="text">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text">${comment.text!}</textarea>
                        </div>
                        <@c.error "comment.text"/>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Write">
                    </div>
                </form>
            </div>
        </div>



        <div class="comments datatable">
            <div class="caption">User</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Comment</th>
                    <th>Text</th>
                    <th>User</th>
                </tr>
                </thead>
                <tbody>
                <#list post.comments as comment>
                    <tr>
                        <td class="id">${comment.id}</td>
                        <td class="post">${comment.post.id}</td>
                        <td class="text">${comment.text}</td>
                        <td class="user">${comment.user.login}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#else>
        No such post
    </#if>
</@c.page>
