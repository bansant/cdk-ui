import { NewHeaderComponent } from './new-header/new-header.component';
import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { UIShellModule, SelectModule, NotificationModule, PlaceholderModule, TilesModule, ModalService } from 'carbon-components-angular';
import { Notification20Module } from '@carbon/icons-angular/lib/notification/20';
import { UserAvatar20Module } from '@carbon/icons-angular/lib/user--avatar/20';
import { AppSwitcher20Module } from '@carbon/icons-angular/lib/app-switcher/20';
import { AppComponent } from './app.component';
import { TranslateModule } from '@ngx-translate/core';
import { UserPopupComponent } from './shared/components/user-popup/user-popup.component';

import { ProgressBarComponent } from './shared/progress-bar/progress-bar.component';
import { TourModule, TourService } from 'ngx-tour-core';
import { TourMatMenuModule } from 'ngx-tour-md-menu';
import { MatProgressBarModule } from '@angular/material';
import { EnvService } from './config/env.service';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { VersionCheckService } from './service-worker/version-check.service';
import { NgIdleModule } from '@ng-idle/core';
import { DatePipe } from '@angular/common';

describe('AppComponent', () => {
	let component: AppComponent;
	let fixture: ComponentFixture<AppComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [
				AppComponent,
				NewHeaderComponent,
				UserPopupComponent,
				ProgressBarComponent
			],
			imports: [
				RouterTestingModule,
				UIShellModule,
				Notification20Module,
				UserAvatar20Module,
				AppSwitcher20Module,
				TranslateModule.forRoot(),
				SelectModule,
				NotificationModule,
				PlaceholderModule,
				TilesModule,
				TourModule,
				TourMatMenuModule.forRoot(),
				MatProgressBarModule,
				HttpClientTestingModule,
				NgIdleModule.forRoot()
			],
			providers: [
				TourService,
				EnvService,
				VersionCheckService,
				ModalService,
				DatePipe
			]
		}).compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(AppComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create the app', async(() => {
		const fixture = TestBed.createComponent(AppComponent);
		const app = fixture.debugElement.componentInstance;
		// expect(app).toBeTruthy();

		expect(component).toBeTruthy();
	}));
});
