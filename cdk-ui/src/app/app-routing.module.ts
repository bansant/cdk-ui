import { OpenRoDetailsComponent } from './open-ro-details/open-ro-details.component';
import { RodetailsComponent } from './rodetails/rodetails.component';
import { RoListComponent } from './ro-list/ro-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
	{
		path: 'ro',
		component: RoListComponent
	},
	{
		path: '',
		component: RoListComponent
	},
	{
		path: 'closedrodetails/:id',
		component: RodetailsComponent
	},
	{
		path: 'openrodetails/:id',
		component: OpenRoDetailsComponent
	},
	
];

@NgModule({
	imports: [
		RouterModule.forRoot(routes,
			{onSameUrlNavigation: 'reload'}
			)
	],
	exports: [RouterModule]
})
export class AppRoutingModule { }
