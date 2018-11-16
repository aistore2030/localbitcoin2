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


            <div class="row">
                <div class="col-md-4">
                    <input autocomplete="off" class="input-xlarge textinput textInput form-control" ng-model="x.margin" id="id_address_to" name="margin" placeholder="{{x.margin}}" type="text" />
                </div>
                <br>
                <br>
            </div>
            <label for="id_address_to" class="control-label requiredField">
                Bonus Value
            </label>
            <div class="row">
            <div class="col-md-4">
                <input autocomplete="off" class="input-xlarge textinput textInput form-control" ng-model="x.bonus" id="id_address_to" name="bonus" placeholder="{{x.bonus}}" type="text" />
            </div>
                </div>
        </div>

    </div>







    <button type="submit" ng-click="Submit(x)" class="btn btn-success">Create Trade Request</button>
    <!-- end new -->




</form>