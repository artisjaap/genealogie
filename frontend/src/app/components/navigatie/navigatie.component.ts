import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {LoginWidgetComponent} from "../../login/components/login-widget/login-widget.component";

@Component({
    selector: 'app-navigatie',
    imports: [
        RouterLink,
        LoginWidgetComponent
    ],
    templateUrl: './navigatie.component.html',
    styleUrl: './navigatie.component.scss'
})
export class NavigatieComponent {

}
