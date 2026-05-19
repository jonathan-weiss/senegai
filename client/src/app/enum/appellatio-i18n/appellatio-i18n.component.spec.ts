import {AppellatioI18nComponent} from "@app/enum/appellatio-i18n/appellatio-i18n.component";
import {AppellatioEnum} from "@app/wto/appellatio.enum";
import {ComponentFixture, TestBed} from "@angular/core/testing";

describe('AppellatioI18n', () => {
    let component: AppellatioI18nComponent;
    let fixture: ComponentFixture<AppellatioI18nComponent>;

    beforeEach(() => {
        fixture = TestBed.createComponent(AppellatioI18nComponent);
        component = fixture.componentInstance;
    });

    it('should display correct text for SENIOR', () => {
        component.enumValue = AppellatioEnum.SENIOR;

        fixture.detectChanges();

        expect(fixture.nativeElement.textContent).toBe('SENIOR');
    });

    it('should display correct text for MATRONA', () => {
        component.enumValue = AppellatioEnum.MATRONA;

        fixture.detectChanges();

        expect(fixture.nativeElement.textContent).toBe('MATRONA');
    });

    // checking if everything is translated
    Object.values(AppellatioEnum).forEach((value) => {
        it(`should translate ${value}`, () => {
            component.enumValue = value;

            fixture.detectChanges();

            expect(fixture.nativeElement.textContent).not.toBe('');
        });
    });
});
