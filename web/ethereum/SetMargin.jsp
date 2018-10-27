<%-- 
    Document   : SetMargin
    Created on : Jan 15, 2018, 12:26:23 PM
    Author     : Saksham
--%>
<form >

    <div class="field-row " id="row_id_address_to">



        <div id="div_id_address_to" class="label-vertical form-group">

            <label for="id_address_to" class="control-label requiredField">
                Margin Value
            </label>


            <div class="">
                <input autocomplete="off" class="input-xlarge textinput textInput form-control" ng-model="x.margin" id="id_address_to" name="margin" placeholder="{{x.margin}}" type="text" />
            </div>
        </div>

    </div>







    <button type="submit" ng-click="Submit(x)" class="btn btn-success">Create Trade Request</button>
    <!-- end new -->




</form>