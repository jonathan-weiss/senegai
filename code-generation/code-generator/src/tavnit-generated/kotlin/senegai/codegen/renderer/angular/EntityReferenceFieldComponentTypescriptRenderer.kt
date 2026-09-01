/*
 * This file is generated using tavnit.
 */
package senegai.codegen.renderer.angular

import senegai.codegen.renderer.model.ui.UiEntityModel

/**
 * Generate the content for the template `EntityReferenceFieldComponentTypescriptRenderer`.
 *
 * This template renderer was generated from the template:
 * - file: `entitas-relata-membrum-relatum-reference-field.component.ts`
 * - path: `entitas-relata/entitas-relata-membrum-relatum-reference-field/entitas-relata-membrum-relatum-reference-field.component.ts`
 */
object EntityReferenceFieldComponentTypescriptRenderer : UiEntityRenderer {

    override fun renderTemplate(model: UiEntityModel): String {
        return """
          |import {Component, Input, OnInit} from '@angular/core';
          |import {FormControl} from "@angular/forms";
          |import {
          |    ${model.entityName.pascalCase}TypeaheadComponent
          |} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}-typeahead/${model.entityName.kebabCase}-typeahead.component";
          |import {
          |    ${model.entityRootItem.itemName.camelCase}DisplayRow,
          |    ${model.entityRootItem.itemName.camelCase}DisplayRowLabel
          |} from "@app/${model.entityName.kebabCase}/${model.entityRootItem.itemName.kebabCase}-display";
          |import {${model.entityName.pascalCase}Service} from "@app/${model.entityName.kebabCase}/${model.entityName.kebabCase}.service";
          |import {
          |    FieldErrorMessagesComponent
          |} from "@app/shared/form-controls/field-error-messages/field-error-messages.component";
          |import {ValidatorTranslation} from "@app/shared/form-controls/validator-translation";
          |import {UUID} from "@app/shared/uuid";
          |import {${model.entityRootItem.itemName.pascalCase}WTO} from "@app/wto/${model.entityRootItem.itemName.kebabCase}.wto";
          |
          |/**
          | * Edits a single reference to an ${model.entityName.pascalCase}, held in the form as one FormControl of a UUID
          | * or null.
          | *
          | * The single-reference counterpart of the ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceTableComponent: the
          | * typeahead itself is the field, it shows the picked entry by its display attributes and a new
          | * pick overwrites it. There is nothing to delete: whether the reference exists at all is decided
          | * one level up by the nullability of the field, and while the field is not null exactly one
          | * reference is required, so an empty field is a validation error.
          | *
          | * The UUID stored in the form says nothing to the user, so it is resolved to the whole
          | * ${model.entityRootItem.itemName.pascalCase}WTO through the already existing backend call (`GET /api/${model.entityName.kebabCase}/{id}`)
          | * and shown by its display attributes. Resolved objects are cached, so a UUID is fetched once.
          | */
          |@Component({
          |    selector: 'app-${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field',
          |    templateUrl: './${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field.component.html',
          |    styleUrls: ['./${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field.component.scss'],
          |    imports: [
          |        ${model.entityName.pascalCase}TypeaheadComponent,
          |        FieldErrorMessagesComponent,
          |    ]
          |})
          |export class ${model.entityName.pascalCase}${model.entityRootItem.itemName.pascalCase}ReferenceFieldComponent implements OnInit {
          |    @Input({required: true}) ${model.entityRootItem.itemName.camelCase}ReferenceFormControl!: FormControl<UUID | null>;
          |    @Input() validatorTranslations: ReadonlyArray<ValidatorTranslation> = [];
          |
          |    private readonly resolvedBy${model.idAttribute.attributeName.pascalCase} = new Map<UUID, ${model.entityRootItem.itemName.pascalCase}WTO>();
          |
          |    constructor(private readonly ${model.entityName.camelCase}Service: ${model.entityName.pascalCase}Service) {}
          |
          |    ngOnInit(): void {
          |        this.refresh();
          |        // The form control is often patched only after the first render, so react to every change.
          |        this.${model.entityRootItem.itemName.camelCase}ReferenceFormControl.valueChanges.subscribe(() => this.refresh());
          |    }
          |
          |    /**
          |     * The display attributes of the referenced entry, shown in the search field itself. Falls
          |     * back to the unresolved value for the attributes that are not known (yet).
          |     */
          |    protected selectionLabel(): string {
          |        const ${model.idAttribute.attributeName.camelCase} = this.referenced${model.idAttribute.attributeName.pascalCase}();
          |        if (${model.idAttribute.attributeName.camelCase} === null) {
          |            return '';
          |        }
          |        return ${model.entityRootItem.itemName.camelCase}DisplayRowLabel(
          |            ${model.entityRootItem.itemName.camelCase}DisplayRow(${model.idAttribute.attributeName.camelCase}, this.resolvedBy${model.idAttribute.attributeName.pascalCase}.get(${model.idAttribute.attributeName.camelCase}))
          |        );
          |    }
          |
          |    protected on${model.entityRootItem.itemName.pascalCase}Selected(${model.entityRootItem.itemName.camelCase}: ${model.entityRootItem.itemName.pascalCase}WTO): void {
          |        this.resolvedBy${model.idAttribute.attributeName.pascalCase}.set(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}, ${model.entityRootItem.itemName.camelCase});
          |        this.${model.entityRootItem.itemName.camelCase}ReferenceFormControl.setValue(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase});
          |    }
          |
          |    private referenced${model.idAttribute.attributeName.pascalCase}(): UUID | null {
          |        return this.${model.entityRootItem.itemName.camelCase}ReferenceFormControl.getRawValue();
          |    }
          |
          |    private refresh(): void {
          |        this.markAsTouchedIfEmpty();
          |        this.resolveMissing${model.entityRootItem.itemName.pascalCase}();
          |    }
          |
          |    /**
          |     * Exactly one reference is required while the field is not null, so an empty enabled field
          |     * is an error the user has to see right away. The typeahead is deliberately not bound to the
          |     * form control, so nothing else ever marks it as touched and the message would stay hidden.
          |     */
          |    private markAsTouchedIfEmpty(): void {
          |        if (this.${model.entityRootItem.itemName.camelCase}ReferenceFormControl.enabled && this.referenced${model.idAttribute.attributeName.pascalCase}() === null) {
          |            this.${model.entityRootItem.itemName.camelCase}ReferenceFormControl.markAsTouched();
          |        }
          |    }
          |
          |    /** The separate backend call that resolves the UUID if it is not yet in the cache. */
          |    private resolveMissing${model.entityRootItem.itemName.pascalCase}(): void {
          |        const ${model.idAttribute.attributeName.camelCase} = this.referenced${model.idAttribute.attributeName.pascalCase}();
          |        if (${model.idAttribute.attributeName.camelCase} === null || this.resolvedBy${model.idAttribute.attributeName.pascalCase}.has(${model.idAttribute.attributeName.camelCase})) {
          |            return;
          |        }
          |        this.${model.entityName.camelCase}Service.get${model.entityRootItem.itemName.pascalCase}ById(${model.idAttribute.attributeName.camelCase})
          |            .subscribe(${model.entityRootItem.itemName.camelCase} => {
          |                if (${model.entityRootItem.itemName.camelCase} !== null) {
          |                    this.resolvedBy${model.idAttribute.attributeName.pascalCase}.set(${model.entityRootItem.itemName.camelCase}.${model.idAttribute.attributeName.camelCase}, ${model.entityRootItem.itemName.camelCase});
          |                }
          |            });
          |    }
          |}
          |
        """.trimMargin(marginPrefix = "|")
    }

    override fun filePath(model: UiEntityModel): String {
      return "${model.entityName.kebabCase}/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field/${model.entityName.kebabCase}-${model.entityRootItem.itemName.kebabCase}-reference-field.component.ts"
    }
}